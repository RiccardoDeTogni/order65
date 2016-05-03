<%-- 
    Document   : homepage
    Created on : 7-mar-2016, 21.09.53
    Author     : Giovanni
--%>

<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Home Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <!--<![endif]-->
<%
    request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="logonManagement" scope="page" class="bflows.LogonManagement" />
<jsp:setProperty name="logonManagement" property="*" />

<!-- Begin Cookie Consent plugin by Silktide - http://silktide.com/cookieconsent -->
<script type="text/javascript">
    window.cookieconsent_options = {"message": "Questo sito utilizza cookie per offrire il miglior servizio possibile. Chiudendo questo banner accetti l'utilizzo dei cookie.", "dismiss": "Chiudi", "learnMore": "Per saperne di più", "link": null, "theme": "dark-top"};
</script>

<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/cookieconsent2/1.0.9/cookieconsent.min.js"></script>
<!-- End Cookie Consent plugin -->

<%
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
    boolean loggedOn = false;
    SessionInfo info = null;
    String status = request.getParameter("status");

    if (status == null) {
        status = "view";
    }

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
        }
    }

    if (status.equals("logon")) {
        logonManagement.logon();
        if ((cookie = logonManagement.getCookie()) != null) {
            response.addCookie(logonManagement.getCookie());
            response.sendRedirect("SearchPage.jsp");
        }
        status = "view";
    }

    if (cookie != null) {
        info = new SessionInfo(cookie);
        loggedOn = info.isLoggedon();
        response.sendRedirect("SearchPage.jsp");
    }

    if (status.equals("logout")) {
        if (loggedOn) {
            logonManagement.setCookie(cookie);
            logonManagement.logout();
            response.addCookie(logonManagement.getCookie());
        }
        loggedOn = false;
    }

%>
<html class="no-js" lang="en">
    <head>

        <!--- Basic Page Needs
   ================================================== -->
        <meta charset="utf-8">
        <title>PlayToday Homepage</title>
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

        <!-- Modernizr
        =================================================== -->
        <script src="js/modernizr.js"></script>
        <script>
    function checkPasswordMatch() {
        var password = $("#register-PASSWORD").val();
        var confirmPassword = $("#register-CONFIRMPASSWORD").val();

        if (password !== confirmPassword)
            alert("Password do not match!");
    }

        </script>
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
                        <a href="homepage.jsp">PlayToday</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">





                    <hr>




                    <!-- Login Form -->
                    <div id="login">
                        <form action="homepage.jsp" method="get" id="loginform" class="group">

                            <input type="text" value="" name="USERNAME" class="username" id="login-USERNAME" placeholder="Inserisci Username" required>
                            <input type="password" value="" name="PASSWORD" class="password" id="login-PASSWORD" placeholder="Inserisci Password" required>
                            <input type="hidden" value="logon" name="status" >
                            <input type="hidden" name="logtype" value="user" >
                            <input type="submit" value="Login" name="login" class="button">



                        </form>

                    </div> <!-- /sign-up form -->      


                </div><!-- /main-content form -->


                <div class="modal-toggles">
                    <ul>
                        <a href="#signin"><li id="registration">Registrati</li></a>
                        <li class="product"><a href="#product">Playtoday</a></li>
                        <li class="about-us"><a href="#mod-about">About us</a></li>
                        <!--  <li class="product"><a href="#mod-product">The product PlayToday</a></li> -->
                    </ul>
                </div><!-- /modal-toggles --> 

                <hr>
                <h1>Il network di giocatori e campi per le tue partite di calcetto</h1>





                <p> Quante volte non hai trovato il campo o i giocatori per le tue partite di calcetto?<br>Sei stanco di gruppi Whatsapp e telefonate?<br>
                    PLAYTODAY ti aiuta a trovare gli amici per giocare e prenotare il campo migliore per le tue esigenze in pochi passaggi.<br>
                    Con PLAYTODAY organizzare le tue partite diventa facile, veloce e divertente.</p>

            </main>	      

        </div><!-- /content-wrap --> 


        <!-- modals
        =================================================== -->


        <section id="mod-about" >

            <!-- Modal toggle -->
            <div class="modal-toggle">
                <a href="#" title="close" id="modal-close">close</a>
            </div>	        	

            <div class="row about-header">

                <div class="twelve columns">	

                    <div class="icon-wrap">
                        <i class="icon"></i>
                    </div>

                    <h1>About Us.</h1>			         

                </div>

            </div> <!-- /about-header -->                   	

            <div class="row about-content">

                <div class="six columns mob-whole">
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam,
                        eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. 
                    </p>

                    <p>Proin gravida nibh vel velit auctor aliquet. Aenean sollicitudin, 
                        lorem quis bibendum auctor, natus error sit voluptatem accusantium nisi elit et quasi architecto beatae vitae dicta sunt explicabo.
                    </p>
                </div>

                <div class="six columns mob-whole contact">

                    <h3>Contact Numbers:</h3>
                    <p>Customer Service: (000) 555 1212<br>
                        Partnerships: (000) 555 0100<br>
                        General Info: (000) 555 0101</p>

                    <h3>Email:</h3>
                    <p>contact@playtoday.com<br>
                        partnerships@playtoday.com
                    </p>

                    <h3>Address:</h3>
                    <p>via Fondobanchetto 17, Ferrara, <br>
                        44121, FE, Italy 
                    </p>

                </div>            

            </div> <!-- /about-content -->

            <div class="slider row">

                <hr>

                <ul id="owl-slider" class="owl-carousel">
                    <li class="item s-photography">
                        <span class="slider-icon"></span>
                        <p>Photography</p>
                    </li>
                    <li class="item s-digital-media">
                        <span class="slider-icon"></span>
                        <p>Digital Media</p>
                    </li>
                    <li class="item s-marketing">
                        <span class="slider-icon"></span>
                        <p>Marketing</p>
                    </li>
                    <li class="item s-packaging">
                        <span class="slider-icon"></span>
                        <p>Packaging</p>    
                    </li>
                    <li class="item s-videography">
                        <span class="slider-icon"></span>
                        <p>Videography</p>
                    </li>                  	
                    <li class="item s-webdesign">
                        <span class="slider-icon"></span>
                        <p>Web Design</p>
                    </li>
                    <li class="item s-branding">
                        <span class="slider-icon"></span>
                        <p>Branding</p>
                    </li>
                    <li class="item s-web-development">
                        <span class="slider-icon"></span>
                        <p>Web Development</p>
                    </li>
                </ul>

            </div><!-- /slider -->			      

        </section><!-- /mod-about -->

        <section id="signin" >

            <!-- Modal toggle -->
            <div class="modal-toggle">
                <a href="#" title="close" id="modal-close">close</a>
            </div>	        	

            <div class="row about-header">

                <div class="twelve columns">	

                    <div class="icon-wrap">
                        <i class="icon"></i>
                    </div>

                    <h1>Registrati</h1>			         

                </div>

            </div> <!-- /about-header -->                   	
            <div id="register">
                <form id="registerform" class="group">
                    <input type="name" value="" name="first_name" class="name" id="register-NAME" placeholder="Nome" required>
                    <input type="surname" value="" name="surname" class="surname" id="register-SURNAME" placeholder="Cognome" required>
                    <input type="city" value="" name="city" class="city" id="register-CITY" placeholder="Città" required>
                    <input type="date" value="" name="data" class="date" id="register-DATE" placeholder="Data di Nascita" required>
                    <input type="phonenumber" value="" name="telephone" class="phone" id="register-PHONENUMBER" placeholder="Numero di Telefono" required>
                    <input type="username" value="" name="username" class="username" id="register-USERNAME" placeholder="Username" required>
                    <input type="password" value="" name="password" class="password" id="register-PASSWORD" placeholder="Password" required>
                    <input type="password" value="" name="confirmpassword" class="confirmpassword" id="register-CONFIRMPASSWORD" placeholder="Conferma Password" required onBlur="checkPasswordMatch();">
                    <input type="email" value="" name="email" class="email" id="register-EMAIL" placeholder="Indirizzo Email" required>              
                    <input type="submit" value="Registrati" name="register" class="button">



                </form>

            </div>		      

        </section><!-- /signin -->

        <section id="product" >

            <!-- Modal toggle -->
            <div class="modal-toggle">
                <a href="#" title="close" id="modal-close">close</a>
            </div>	        	

            <div class="row about-header">

                <div class="twelve columns">	

                    <div class="icon-wrap">
                        <i class="icon"></i>
                    </div>

                    <h1>Playtoday</h1>			         

                </div>

            </div> <!-- /about-header -->                   	

        </section><!-- /product -->



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

