<%-- 
    Document   : deconnexion
    Created on : 28 mars 2017, 16:25:13
    Author     : nate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% session.invalidate();%> 
        <jsp:forward page="index.jsp"> 
            <jsp:param name="msg" value="msg" /> 
        </jsp:forward> 
    </body>
</html>
