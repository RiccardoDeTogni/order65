<%-- 
    Document   : Wherepage
    Created on : 19-apr-2016, 16.11.36
    Author     : Giovanni
--%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Where Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>

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
                response.sendRedirect("homepage.jsp");
            }
        }

        if (status == null) {
            status = "view";
        }

        reservationManagement.setCitta(info.getCity());
    %>
    <head>

        <!--- Basic Page Needs
   ================================================== -->
        <meta charset="utf-8">
        <title>PlayToday Where</title>
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
        <link rel="stylesheet" href="css/where.css">   

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
                        <a href="homepage.jsp">PlayToday.</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">

                    <h1>PlayToday</h1>



                    <hr>





                    <!-- Available places -->

                    <h2> Calendario per nome struttura: </h2>
                    <div id="calendar">
                        <ul id="datebar">
                            <li>frecciasinistra</li>
                                <% reservationManagement.getReservationsFromCampo();
                                   
                                    {%>
                            <li id="datequalcosa">Data<%=i%></br>x posti disponibili </li>
                                <%}%>
                            <li>frecciadestra</li>
                        </ul>
                        <hr>
                        <div id="slots">

                            <%for (int i = 7; i < 24; i++) {%>
                            <div id="slot">Slot <%=i%>.00-<%=(i + 1)%>.00</div>

                            <%}%> 
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
        <script src="http://maps.google.com/maps/api/js?v=3.13&amp;sensor=false"></script>    
        <script src="js/jquery.fittext.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.placeholder.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/main.js"></script>  
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </body>

</html>
