<script type="text/javascript">
                function dropDown() {
                 document.getElementById("dropmenu").classList.toggle("show");
                }
        </script>



<div class="dropdown">
    <% if(loggedOn){%>
                    <button onclick="dropDown()" class="dropbtn"/>
                        <div id="dropmenu" class="dropdown-content">
                            <p><%=info.getUsername()%></P>
                        <a href="Profilo.jsp">Il mio profilo</a>
                        <% if((info.getUsrType())==2){%>
                        <a href="Prenotazioni.jsp">Le mie prenotazioni</a>
                         <% } else if((info.getUsrType())==3){ %>
                         <a href="Struttura.jsp">La mia struttura</a>
                         <%} else if((info.getUsrType())==1) {%>
                         <a href="#">Paginasegreta</a>
                         <%} %>
                        <a href="homepage.jsp?USERNAME=<%=info.getUsername()%>&status=logout">Logout</a>
                    </div>
                    <%}%>
                </div>