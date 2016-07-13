<%-- 
    Document   : Profilo
    Created on : 19-mag-2016, 14.38.07
    Author     : Giovanni
--%>

<%@page import="blogics.User"%>
<%@page import="services.log.LogTypes"%>
<%@page import="services.log.Logs"%>
<%@page import="blogics.Campo"%>
<%@page import="blogics.Reservation"%>
<%@page import="java.util.List"%>
<%@page import="global.Constants"%>
<%@page import="services.session.SessionInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Profile Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>
<%@ page errorPage="/errorPage.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="logonManagement" scope="page" class="bflows.LogonManagement" />
<jsp:setProperty name="logonManagement" property="*" />

<jsp:useBean id="userManagement" scope="page" class="bflows.UserManagement" />
<jsp:setProperty name="userManagement" property="*" />
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
        userManagement.setInfo(info);

        if (status == null) {
            status = "view";
        }
        if (status.equals("update")) {
            userManagement.modify();
        }
         if (status.equals("updatePwd")) {
            userManagement.modifyPassword();
        }


    %>


    <script>
        function checkPasswordMatch() {
            var password = $("#register-PASSWORD").val();
            var confirmPassword = $("#register-CONFIRMNEWPASSWORD").val();

            if (password !== confirmPassword)
                alert("Password do not match!");
        }
    </script>


    <head>

        <!--- Basic Page Needs
   ================================================== -->
        <meta charset="utf-8">
        <title>PlayToday Profilo Utente</title>
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


        <script>
           function toggle(){
            var toggle = document.getElementById("changepassword");
            var button1 = document.getElementById("togglepasswordchange");
            var button2 = document.getElementById("hidepasswordchange");
            toggle.style.display = toggle.style.display === 'none' ? '' : 'none';
            button1.style.display = button1.style.display === 'none' ? '' : 'none';
            button2.style.display = button2.style.display === 'none' ? '' : 'none';
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
                
                <%@ include file="dropdown.jsp"%>

                <header class="site-header">
                    <div class="logo">
                        <a href="index.jsp">PlayToday</a>
                    </div> 
                </header>

                <div id="main-content" class="twelve columns">

                    <h1> Il mio profilo </h1>

                    <div id="Info">
                        <% if (status.equals("update")) { %>
                        <h2>I dati sono stati modificati correttamente!</h2>
                        <% }%>
                        <h3>Username: <%=info.getUsername()%> </h3>
                        <%  userManagement.setUsername(info.getUsername());
                            User u = userManagement.getUser();%>
                        <form id="profilechange" action="Profilo.jsp" method="POST">
                            <input type="name" value="<%=u.getFirst_name()%>" name="first_name" class="name" id="register-NAME">
                            <input type="surname" value="<%=u.getSurname()%>" name="surname" class="surname" id="register-SURNAME">
                            <input type="city" value="<%=u.getCity()%>" name="city" class="city" id="register-CITY">
                            <input type="date" value="<%=u.getBirthdate()%>" name="data_temp" class="date" id="register-DATE">
                            <input type="telephone" value="<%=u.getTelephone()%>" name="telephone" class="phone" id="register-PHONENUMBER">
                            <input type="email" value="<%=u.getEmail()%>" name="email" class="email" id="register-EMAIL" disabled>
                            <input type="hidden" name="status" value="update">
                            
                            <input type="submit" value="Modifica" name="modifica" class="button">
                        </form>
                            
                            <button id="togglepasswordchange" onClick="toggle()"> Cambia la password </button>
                        <form id="changepassword" style="display:none" action="">
                            <input type="password" value="" name="oldpassword" class="oldpassword" id="register-OLDPASSWORD" placeholder="Vecchia Password"> 
                            <input type="password" value="" name="passwd" class="newpassword" id="register-PASSWORD" placeholder=" Nuova Password" required>
                            <input type="hidden" name="status" value="updatePwd">
                            <input type="hidden" name="username" value="<%=userManagement.getUsername()%>" >
                            <input type="password" value="" name="confirmnewpassword" class="confirmnewpassword" id="register-CONFIRMNEWPASSWORD" placeholder="Conferma Nuova Password" required onBlur="checkPasswordMatch();">
                            <input type="submit" value="Cambia Password" name="cambiapassword" class="button">
                        </form>

                    </div>




                    <button id="hidepasswordchange" onClick="toggle()" style="display: none"> Annulla </button>








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
