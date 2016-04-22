<%-- 
    Document   : Wherepage
    Created on : 19-apr-2016, 16.11.36
    Author     : Giovanni
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="Where Page" %>
<%@ page contentType="text/html" %>
<%@ page session="false" %>
<%@ page buffer="30kb" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->

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
        <link rel="stylesheet" href="css/when.css">   

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



<script type="text/javascript">
                        $('#calendar').fullCalendar({
	header: {
		left: 'prev,next today',
		center: 'title',
		right: 'month,agendaWeek,agendaDay'
	},
	businessHours: {
		start: '10:00',
		end: '18:00',
		dow: [1, 2, 3, 4]
	},
	defaultDate: '2014-06-12',
	defaultView: 'agendaWeek',
	editable: true,
	events: [{
		title: 'All Day Event',
		start: '2014-06-01'
	}, {
		title: 'Long Event',
		start: '2014-06-07',
		end: '2014-06-10'
	}, {
		id: 999,
		title: 'Repeating Event',
		start: '2014-06-09T16:00:00'
	}, {
		id: 999,
		title: 'Repeating Event',
		start: '2014-06-16T16:00:00'
	}, {
		title: 'Meeting',
		start: '2014-06-12T10:30:00',
		end: '2014-06-12T12:30:00'
	}, {
		title: 'Lunch',
		start: '2014-06-12T12:00:00'
	}, {
		title: 'Birthday Party',
		start: '2014-06-13T07:00:00'
	}, {
		title: 'Click for Google',
		url: 'http://google.com/',
		start: '2014-06-28'
	}]
});
 </script>

                    <!-- Available places -->

                    <h2> Calendario per nome struttura: </h2>
                    <div id="calendar"> </div>
                    


                        
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
