<%-- 
    Document   : errorPage
    Created on : 13-lug-2016, 10.52.33
    Author     : Riccardo
--%>

<%@page import="java.io.*"%>
<%@ page import="services.session.SessionType"%>
<%@ page import="services.log.*"%>
<%@ page import="services.session.*"%>
<%@ page import="services.error.*"%>
<%@ page info="Error Page" %>
<%@ page session="false" %>
<%@ page buffer="32kb" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


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
        <link rel="stylesheet" href="css/landingpage.css">   

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
                        <a href="index.jsp">PlayToday.</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">
                    <h1>Si è verificato un errore...</h1>
                    <a href="index.jsp">Torna alla pagina iniziale</a>
                    <!--
                    <%if (exception != null) {
                            String message = (exception.getMessage() != null) ? exception.getMessage() : "Nessun Messaggio";
                            ByteArrayOutputStream stackTrace = new ByteArrayOutputStream();
                            exception.printStackTrace(new PrintWriter(stackTrace, true));%>
                    <b>Messaggio di Errore: </b><%=message%><br/>
                    <p><%=stackTrace.toString()%></p>
                    <%} else { %>
                    ERRORE!! Eccezione NULLA<br/>
                    No Stack Trace
                    <%}%> -->

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
<script src="js/main.js"></script>  
<script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>

</html>

