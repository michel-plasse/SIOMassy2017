<%-- 
    Document   : trombinoscope
    Created on : 24 mars 2017, 14:25:33
    Author     : nate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Trombinoscope"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trombinoscope</title>
    </head>
    <body>
        <table border="1">
            <th>Nom</th>
            <th>Prénom</th>
            <th>Photo</th>

            <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
                <tr>
                    <td><c:out value="${unePersonne.nom}" /></td>
                    <td><c:out value="${unePersonne.prenom}" /></td>
                    <td><img height="42" width="42" src="image/trombi/<c:out value="${unePersonne.photo}"/>"/></td>
                  
                </tr>  
            </c:forEach>
        </table>

    </body>
</html>
