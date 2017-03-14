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
        <h1>La liste de candidatures</h1>
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Statut</th>
                <th>Date_debut</th>
                <th>Date_fin</th>
                <th>Session</th>
                <th>Date_de_postulation</th>
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
