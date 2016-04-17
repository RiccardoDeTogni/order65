<%-- 
    Document   : index
    Author     : Riccardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Campo> cl = ReservationManagement.getFreeCampoFromDateTime();
    Campo tmp = new Campo(,,);
   for(Campo c : cl){
       if(c.getId_Struttura() != tmp.getId_Struttura){
       %>
       <text> <%=c.getNomeStruttura()%> </text>
       <%
       }
       tmp = c;
   } 
%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Play Today</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
