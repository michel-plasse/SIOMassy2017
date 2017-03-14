<%-- 
    Document   : candidatures
    Created on : 13 mars 2017, 19:42:28
    Author     : AveigA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! candidatures</h1>
        <table border="1">
            <tr>
                <th>nom</th>
                <th>prenom</th>
                <th>statut</th>
                <th>debut</th>
                <th>fin</th>
                <th>formation</th>
                <th>date</th>
            </tr>
        <c:forEach var="unCandidature" items="${lesCandidatures}">
            <tr>
                <td>${unCandidature["nom"]}</td>
                <td>${unCandidature["prenom"]}</td>
                <td>${unCandidature["statut"]}</td>
                <td>${unCandidature["debut"]}</td>
                <td>${unCandidature["fin"]}</td>
                <td>${unCandidature["formationNom"]}</td>
                <td>${unCandidature["effectue"]}</td>
            </tr>
            
        </c:forEach>
        </table>
    </body>
</html>
