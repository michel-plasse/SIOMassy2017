
        <% session.invalidate();%> 
        <jsp:forward page="index.jsp"> 
            <jsp:param name="msg" value="msg" /> 
        </jsp:forward> 

