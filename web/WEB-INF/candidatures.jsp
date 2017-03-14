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
        <form action="">
            <table>
                <tr>
                    <td>
                        <select name="nom">
                            <option value="" selected="">choisissez un nom</option>
                            <c:forEach var="unCandidature" items="${lesCandidatures}">                
                                <option value="${unCandidature["nom"]}">${unCandidature["nom"]}</option>                           
                            </c:forEach>
                        </select>
                    </td>
                        
                    <td>
                        <select name="prenom">
                             <option value="" selected="">choisissez un prenom</option>
                            <c:forEach var="unCandidature" items="${lesCandidatures}">                
                                <option value="${unCandidature["prenom"]}">${unCandidature["prenom"]}</option>                           
                            </c:forEach>
                             
                         </select>
                    </td>
                    <td>
                         <select name="statut">
                              <option value="" selected="">choisissez un statut</option>
                            <c:forEach var="unCandidature" items="${lesCandidatures}">                
                                <option value="${unCandidature["statut"]}">${unCandidature["statut"]}</option>                           
                            </c:forEach>
                         </select>
                    </td>
                    <td>
                         <select name="formationNom">
                              <option value="" selected="">choisissez une session</option>
                             <c:forEach var="unCandidature" items="${lesCandidatures}">                
                                <option value="${unCandidature["formationNom"]}">${unCandidature["formationNom"]}</option>                           
                             </c:forEach>
                              
                         </select>
                    </td>
                    <td>
                         <select name="DateDePostulation">
                              <option value="" selected="">choisissez une date </option>
                                <c:forEach var="unCandidature" items="${lesCandidatures}">                
                                <option value="${unCandidature["effectue"]}">${unCandidature["effectue"]}</option>                           
                            </c:forEach>
                         </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Rechercher">
        </form>
           
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
