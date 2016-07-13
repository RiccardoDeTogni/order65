<%-- 
    Document   : SearchPage
    Created on : 22-mar-2016, 16.18.10
    Author     : Giovanni
--%>

<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="blogics.Campo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="When Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>
<%@ page errorPage="/errorPage.jsp" %>

<jsp:useBean id="reservationManagement" scope="page" class="bflows.ReservationManagement" />
<jsp:setProperty name="reservationManagement" property="*" />
<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->

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
            } else {
                response.sendRedirect("index.jsp");
            }
        }

        if (status == null) {
            status = "view";
        }
        
        
        reservationManagement.setCitta(info.getCity());
    %>    

    <script>
        function reserv(idcampo){
            document.getElementById("campo").value = idcampo;
        }
    </script>
    <head>

        <!--- Basic Page Needs
   ================================================== -->
        <meta charset="utf-8">
        <title>PlayToday When</title>
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
        <link rel="stylesheet" href="css/when.css">  
         <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

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
                
                <%@ include file="dropdown.jsp"%>

                <header class="site-header">
                    <div class="logo">
                        <a href="index.jsp">PlayToday.</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">

                    <h1>PlayToday</h1>



                    <hr>





                    <!-- Available places -->

                    <h2> Strutture disponibili il <%=reservationManagement.getData_temp()%> dalle <%=reservationManagement.getOra_inizio_temp()%> alle <%=reservationManagement.getOra_fine_temp()%>: </h2>
                    <div class="placevisualization"> 


                        <div>
                            <% List<Campo> cl = reservationManagement.getFreeCampoFromDateTime();
                                Campo tmp = new Campo(0, "");
                                int i = 1;
                                for (Campo c : cl) {

                                    if (c.getId_struttura() != tmp.getId_struttura()) {
                            %>
                        </div>
                        <a href="#toggleField<%=i%>" data-toggle="collapse"/><div id="place<%=i%>" attr="<%=i%>" > <%=c.getNome_struttura()%></div>
                        <div id="toggleField<%=i%>" class="collapse">
                            <%
                                    i++;
                                }
                            %>
                            <a href="#"/><div id="field" onClick="reserv(<%=c.getId()%>)" class="field" ><%=c.getNome()%></div>
                            <%
                                    tmp = c;
                                }
                            %>
                        </div>
                        <form id="insertReservation" action="landingpage.jsp" method="get" >
                            <input type="hidden" id="start" name="ora_inizio_temp" value="<%=reservationManagement.getOra_inizio_temp()%>">
                            <input type="hidden" id="end" name="ora_fine_temp" value="<%=reservationManagement.getOra_fine_temp()%>">
                            <input type="hidden" id="date" name="data_temp" value="<%=reservationManagement.getData_temp()%>">
                            <input type="hidden" id="campo" name="id_campo" value="">
                            <input type="hidden" name="status" value="insertReservation">
                            <input type="hidden" name="id_user" value="<%=info.getId()%>">
                        </form>
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
        <script src="js/main.js"></script>  
        <script src="js/confirm.js"></script>  
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </body>

</html>
