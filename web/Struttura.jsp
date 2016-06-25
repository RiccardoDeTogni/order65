<%-- 
    Document   : Struttura
    Created on : 3-mag-2016, 16.06.38
    Author     : Giovanni
--%>

<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="blogics.Reservation"%>
<%@page import="blogics.Campo"%>
<%@page import="java.util.List"%>
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
        <link rel="stylesheet" href="css/struttura.css">   

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

                    <h1> Struttura: </h1>


                    <ul class="nav nav-tabs-justified">

                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Campi
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <% for (int i = 0; i < 10; i++) {%>
                                <li><a href="#">Home</a></li>
                                    <%}%>
                            </ul>
                        </li>
                        <li><a data-toggle="tab" href="#inserimento">Inserisci prenotazione</a></li>
                        <li><a data-toggle="tab" href="#orari">Imposta orari</a></li>

                    </ul>


                    <div id="campi" class="tab-pane fade in active"></div>


                    <%  reservationManagement.setId_campo(1);
                        List<Reservation> rl = reservationManagement.getReservationsFromCampo(); %>

                    <% for (int i = 0; i < 7; i++) {%>

                    <div id="date<%=i%>">data prova</div>
                    <div id="slots">

                        <%
                            
                            if (!rl.isEmpty()) {

                                for (int k = 7; k < 24; k++) {
                                    Boolean bool = false;
                                    for (Reservation r : rl) {

                                        String tmp = (i < 10 ? "0" : "") + i + ":00:00";

                                        if ((r.getOra_inizio().toString().equals(tmp))) {
                                            bool = true;
                                        }
                                    }
                                    if (bool) {
                        %>

                        <div id="slot<%=k%>" class="dayslot" style="background-color:red">Slot <%=k%>.00-<%=(k + 1)%>.00</div> 
                        <%  } else {%>
                        <a href="#"><div id="slot<%=k%>" class="slot">Slot <%=k%>.00-<%=(k + 1)%>.00</div></a>
                        <%
                                    }
                                }
                            }%> 
                    </div>

                    <%}%>
                    <div id="inserimento" class="tab-pane fade"></div>

                    <div id="orari" class="tab-pane fade">
                        Imposta orario di apertura:
                        <input type="apertura" name="apertura" value="">
                        </br>
                        Imposta orario di chiusura:
                        <input type="chiusura" name="apertura" value="">

                        Imposta giorno di chiusura:
                        <select  name="giornolibero">
                            <option value="Lunedi">Lunedì</option>
                            <option value="Martedi">Martedì</option>
                            <option value="Mercoledi">Mercoledì</option>
                            <option value="Giovedi">Giovedì</option>
                            <option value="Venerdi">Venerdì</option>
                            <option value="Sabato">Sabato</option>
                            <option value="Domenica">Domenica</option>
                        </select>
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
