<div class="dropdown">
    <% if(loggedOn){%>
                    <button onclick="dropDown()" class="dropbtn"/>
                        <div id="dropmenu" class="dropdown-content">
                            <p><%=info.getUsername()%></P>
                        <a href="Profilo.jsp">Il mio profilo</a>
                        <% if((info.getUsrType())==1){%>
                        <a href="Prenotazioni.jsp">Le mie prenotazioni</a>
                         <% } else { %>
                         <a href="Struttura.jsp">La mia struttura</a>
                         <%}%>
                        <a href="homepage.jsp?USERNAME=<%=info.getUsername()%>&status=logout">Logout</a>
                    </div>
                    <%}%>
                </div>