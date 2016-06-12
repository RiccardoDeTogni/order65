<%-- 
    Document   : Wherepage
    Created on : 19-apr-2016, 16.11.36
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
        <link rel="stylesheet" href="css/where.css">   

        <!-- Modernizr
        =================================================== -->
        <script src="js/modernizr.js"></script>


        <!-- Favicons
        =================================================== -->
        <link rel="shortcut icon" href="favicon.png" >

    </head>

    <body>

        <script>
            function reserv(orario) {
                
                var inizio = document.getElementById("ora_inizio_temp");
                inizio.value = "" + orario + ".00";
                
                var fine = document.getElementById("ora_fine_temp");
                fine.value = "" + (orario + 1) + ".00";
            }
        </script>
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

                    <ul class="nav nav-pills nav-stacked pull-left">


                        <% List<Campo> cl = reservationManagement.getCampoListFromStruttura();

                            for (Campo c : cl) {%>

                        <li>
                            <form id="changefield<%=c.getNome()%>" action="Wherepage.jsp" method="get">
                                <input type="hidden" name="id_campo" value="<%=c.getId()%>" id="search-field">
                                <input type="hidden" id="tags" name="nome_struttura" value="<%=reservationManagement.getNome_struttura()%>">
                                <input type="hidden" value="<%=reservationManagement.getData_temp()%>" name="data_temp" class="date" id="search-DATE">
                                <button class="btn btn-toolbar" role="link" type="submit" form="changefield<%=c.getNome()%>" name="search" value="<%=c.getNome()%>">
                            </form>
                        </li>
                        <%}%>

                    </ul>

                    <%if (reservationManagement.getId_campo() == 0) {
                            reservationManagement.setId_campo(cl.get(0).getId());
                        }%>

                    <!-- Available places -->

                    <h2> Calendario per <%=reservationManagement.getNome_struttura()%> </h2>
                    <h2><%=reservationManagement.getNomeCampo_StrutturaFromId().getNome()%></h2>
                    <div id="calendar" class="six-columns">
                        <ul id="datebar">
                            <li>frecciasinistra</li>
                            <li id="datebefore">Data<%%></br>x posti disponibili </li>
                            <li id="dateselected">Data<%=reservationManagement.getData_temp()%></br>x posti disponibili </li>
                                <li id="dateafter">Data<%%></br>x posti disponibili </li>
                                <li>frecciadestra</li>
                            </ul>
                            <hr>
                            <% List<Reservation> rl = reservationManagement.getReservationsFromCampo();%>
                            <div id="slots">

                                <%if (!rl.isEmpty()) {
                                        
                                        for (int i = 7; i < 24; i++) {
                                            for (Reservation r : rl) {
                                                
                                            String tmp = (i<10 ? "0" : "") + i + ":00:00";
                                            
                                            if ((r.getOra_inizio().toString().equals(tmp))) {%>
                                            
                                <div id="slot<%=i%>" style="background-color:red">Slot <%=i%>.00-<%=(i + 1)%>.00</div> 
                                <%  } else {%>
                                <a href="#"><div id="slot<%=i%>" onClick="reserv(<%=i%>)" class="slot">Slot <%=i%>.00-<%=(i + 1)%>.00</div></a>
                                <%}
                                        }
                                    }
                                } else {
                                    
                                       for (int i = 7; i < 24; i++) {%>
                                       
                                       <a href="#"><div id="slot<%=i%>" onClick="reserv(<%=i%>)" class="slot">Slot <%=i%>.00-<%=(i + 1)%>.00</div></a>
                                <%
                                        }
                                    }%> 
                            </div>

                            <form id="insertReservation" action="landingpage.jsp" method="get" >
                                <input type="hidden" name="ora_inizio_temp" id="ora_inizio_temp" value="">
                                <input type="hidden" name="ora_fine_temp" id="ora_fine_temp" value="">
                                <input type="hidden" id="date" name="data_temp" value="<%=reservationManagement.getData_temp()%>">
                                <input type="hidden" id="campo" name="id_campo" value="<%=reservationManagement.getId_campo()%>">
                                <input type="hidden" name="status" value="insertReservation">
                                <input type="hidden" name="id_user" value="<%=info.getId()%>">
                            </form>

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
        <script src="js/confirm.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </body>

</html>
