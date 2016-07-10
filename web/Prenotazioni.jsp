<%-- 
    Document   : Prenotazioni
    Created on : 3-mag-2016, 15.35.09
    Author     : Giovanni
--%>

<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="blogics.Campo"%>
<%@page import="blogics.Reservation"%>
<%@page import="java.util.List"%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Prenotazioni Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>
<%
    request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="logonManagement" scope="page" class="bflows.LogonManagement" />
<jsp:setProperty name="logonManagement" property="*" />

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
        if (status.equals("deleteReservation")) {
            reservationManagement.delete();
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
        <link rel="stylesheet" href="css/prenotazioni.css">   

        <!-- Modernizr
        =================================================== -->
        <script src="js/modernizr.js"></script>


        <!-- Favicons
        =================================================== -->
        <link rel="shortcut icon" href="favicon.png" >
        
        <script type="text/javascript">
            function setId(reservid) {
                //ciao
                document.getElementById("id_reservation").value = reservid;
                alert("lol");
            }
        </script>
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
                    <% if (!status.equals("deleteReservation")) {%>
                    <h1> Le mie prenotazioni </h1>

                    <% reservationManagement.setId_user(info.getId());
                        List<Reservation> resList = reservationManagement.getReservationsFromUser();
                        int i = 0;
                        for (Reservation res : resList) {
                            i++;
                    %>

                    <div id="field<%=i%>" > Dettagli prenotazione:
                        <% reservationManagement.setId_campo(res.getId_campo());

                            Campo c = reservationManagement.getNomeCampo_StrutturaFromId();
                        %>

                        Struttura: <%=c.getNome_struttura()%><br/>
                        Campo: <%=c.getNome()%><br/>
                        Data: <%=res.getData()%><br/>
                        Orario: <%=res.getOra_inizio()%>-<%=res.getOra_fine()%><br/>
                        Codice Prenotazione: <%=res.getCode()%><br/>
                        <button id="delete" onClick="setId(<%=res.getId()%>)">X</button>

                    </div>
                    <%}%>




                    <form id="deleteReservation" action="Prenotazioni.jsp" method="get" >
                        <input type="hidden" name="id" id="id_reservation" value="">
                        <input type="hidden" name="status" value="deleteReservation">
                        <input type="hidden" name="id_user" value="<%=info.getId()%>">
                    </form>

                    <% } else {%>
                    <h4>La prenotazione è stata rimossa con successo...</h4>
                    <a href="SearchPage.jsp"><div id="ricerca">Effettua un'altra ricerca</div></a>

                    <% }%>


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
