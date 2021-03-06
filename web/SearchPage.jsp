<%-- 
    Document   : SearchPage
    Created on : 22-mar-2016, 16.18.10
    Author     : Giovanni
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="java.util.List"%>
<%@page import="blogics.Struttura"%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Home Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>
<%@ page errorPage="/errorPage.jsp" %>

<jsp:useBean id="reservationManagement" scope="page" class="bflows.ReservationManagement" />
<jsp:setProperty name="reservationManagement" property="*" />

<%
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
    boolean loggedOn = false;
    SessionInfo info = null;
    String status = request.getParameter("status");

    if (cookies != null) {
        cookie = null;
        for (Cookie c : cookies) {
            if (Constants.COOKIE_NAME.equals(c.getName())) {
                cookie = c;
            }
        }
        if (cookie != null) {
            info = new SessionInfo(cookie);
            loggedOn = info.isLoggedon();
            if (!loggedOn) {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    if (status == null) {
        status = "view";
    }
    reservationManagement.setCitta(info.getCity());
%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->

    <head>

        <!--- Basic Page Needs
   ================================================== -->
        <meta charset="utf-8">
        <title>PlayToday Search</title>
        <meta name="description" content="Giovanni Bucci">  
        <meta name="author" content="Giovanni Bucci">

        <!-- Mobile Specific Metas
        ================================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <!-- CSS
        ================================================== -->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
           
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/vendor.css">
        <link rel="stylesheet" href="css/homepage.css">  
         <link rel="stylesheet" href="css/search.css">
        
        <!-- Modernizr
        =================================================== -->
        <script src="js/modernizr.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <!-- Favicons
        =================================================== -->
        <link rel="shortcut icon" href="favicon.png" >
        
        <!-- Autocomplete Script -->
        <script type="text/javascript">

            var jQuery_1_11_0 = $.noConflict(true);
            jQuery_1_11_0(document).ready(function () {
                var availableTags = [<%List<Struttura> str = reservationManagement.getStrutturaListFromCity();
                    for (Struttura s : str) {%>"<%=s.getNome()%>", <%}%>""];
                        jQuery_1_11_0("#tags").autocomplete({
                    source: availableTags
                });
            });
        </script>

        
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
      
      <!--  <script>
            $.getJSON('https://geoip-db.com/json/geoip.php?jsonp=?') 
               .done (function(location)
               {
                  $('#country').html(location.country_name);
                  $('#state').html(location.state);
                  $('#city').html(location.city);
                  $('#latitude').html(location.latitude);
                  $('#longitude').html(location.longitude);
                  $('#ip').html(location.IPv4);               
               });
          </script>
        -->
    </head>

    <body>

        <!--[if lt IE 9]>
             <p class="browserupgrade">You are using an <strong>outdated</strong> browser. 
             Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->   

        <!-- content-wrap -->
        <div id="content-wrap">

            <!-- main  -->
            <main class="row">
                    
                <%@ include file="dropdown.jsp"%>   
                
                
                <header class="site-header">
                    <div class="logo">
                        <a href="index.jsp">PlayToday.</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">

                  


                    <!--<div>Non sei a <text id="state"></text>?</div> -->
                            



                    <!-- Search Form -->
                    <div class="search">
                        
                                  <ul class="nav nav-pills nav-justified">
                                      <li class="active"><a data-toggle="pill" href="#dove">Dove?</a></li>
                                      <li> <a data-toggle="pill" href="#quando">Quando?</a></li>
                                  </ul>
                        

                        
                                  <div class="tab-content">
                            
                                             <div id="dove" class="tab-pane fade in active"> 

                                                     <h2>Dove?</h2>
                                                     
                                                     <% String data_temp = "";
                                                        
                                                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                                        data_temp = formatter.format(Calendar.getInstance().getTime());
                                                     %>

                                                             <form id="placesearchform" action="Wherepage.jsp" class="group">
                                                                     <input type="hidden" name="id_campo" value="0" id="search-field">
                                                                     <input type="hidden" value="<%=data_temp%>" name="data_temp" class="date" id="search-DATE">
                                                                     <input type="name" id="tags" name="nome_struttura">
                                                                     <input type="submit" value="Cerca" name="search" class="button">
                                                             </form>
                                             </div>
                                      
                                      
                            
                                             <div id="quando" class="tab-pane fade">

                                                      <h2>Quando?</h2>


                                                             <form id="placesearchform" action="Whenpage.jsp" class="group">
                                                                     <input type="date" value="" name="data_temp" class="date" id="search-DATE" placeholder="Data" required>
                                                                     <input type="startime" value="" name="ora_inizio_temp" class="startime" id="search-STARTIME" placeholder="Ora inizio" required>
                                                                     <input type="endtime" value="" name="ora_fine_temp" class="endtime" id="search-ENDTIME" placeholder="Ora fine" required>

                                                                     <input type="submit" value="Cerca" name="search" class="button">

                                                             </form>
                                             </div>
                                 </div>
                     </div>

                 </div>
            </main>	      

        </div><!-- /content-wrap --> 


        <!-- footer
   =================================================== -->
        <%@ include file="footer.html"%> 
        <!-- Script
        =================================================== -->
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/jquery-migrate-1.2.1.min.js"></script>
        <script src="js/jquery.fittext.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.placeholder.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>    

    </body>

</html>
