<%-- 
    Document   : SearchPage
    Created on : 22-mar-2016, 16.18.10
    Author     : Giovanni
--%>

<%@page import="java.util.List"%>
<%@page import="blogics.Struttura"%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Home Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>

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
        }else{
            response.sendRedirect("homepage.jsp");
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
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/vendor.css">
        <link rel="stylesheet" href="css/homepage.css">    
        <link rel="stylesheet" href="css/search.css">   
        <!-- Modernizr
        =================================================== -->
        <script src="js/modernizr.js"></script>


        <!-- Favicons
        =================================================== -->
        <link rel="shortcut icon" href="favicon.png" >

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

                <header class="site-header">
                    <div class="logo">
                        <a href=".html">PlayToday.</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">

                    <h1>PlayToday</h1>



                    <hr>



                    <!-- Search Form -->
                    <div class="search">
                        <nav>
                            <ul class="tabs">
                                <li><a data-content="where" class="selected" href="#0">Dove?</a></li>
                                <li><a data-content="when" href="#0">Quando?</a></li>
                            </ul>
                        </nav>

                        <ul class="contents">
                            <li data-content="where" class="selected"> 

                                <h2>Dove?</h2>


                                <form id="placesearchform" class="group">
                                    <select name="Struttura">
                                    <% List<Struttura> sl = reservationManagement.getStrutturaListFromCity();
                                       for(Struttura s : sl){ %>
                                       <option type="name" value="<%=s.getId()%>" name="FIELDNAME" class="fieldname" id="search-FIELDNAME" placeholder="Struttura" required><%=s.getNome()%></option>
                                    <% } %>
                                    </select>

                                    <input type="submit" value="Cerca" name="search" class="button">



                                </form>
                            </li>
                            <li data-content="when">

                                <h2>Quando?</h2>


                                <form id="placesearchform" class="group">

                                    <input type="date" value="oggi" name="DATE" class="date" id="search-DATE" placeholder="Data" required>
                                    <input type="startime" value="startime" name="STARTIME" class="startime" id="search-STARTIME" placeholder="Ora inizio" required>
                                    <input type="endtime" value="endtime" name="ENDTIME" class="endtime" id="search-ENDTIME" placeholder="Ora fine" required>
                                    <input type="hidden" value="city" name="CITY" class="city" id="search-CITY" required>

                                    <input type="submit" value="Cerca" name="search" class="button">



                                </form>
                            </li>
                        </ul>
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
        <script src="http://maps.google.com/maps/api/js?v=3.13&amp;sensor=false"></script>    
        <script src="js/jquery.fittext.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.placeholder.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/main.js"></script>    

    </body>

</html>