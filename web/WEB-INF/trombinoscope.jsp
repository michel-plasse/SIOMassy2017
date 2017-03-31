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
        <div>
        <div>
            <ul>
                <li>
                    <a href="#">Formation</a>
                </li>
                <li>
                    <a href="#">BTS 1</a>
                </li>
                <li>
                    <a href="#">BTS 2</a>
                </li>
                <li>
                    <a href="#">BTS 3</a>
                </li>
                <li>
                    <a href="#">Formateurs</a>
                </li>
            </ul>
        </div>
        </div>



        <div class="contenu">
            <table border="1">
                <th>Nom</th>
                <th>Prénom</th>
                <th>Photo</th>
                <th>Numéro de téléphone</th>
                <th>Email</th>

                <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
                    <tr>
                        <td><c:out value="${unePersonne.nom}" /></td>
                        <td><c:out value="${unePersonne.prenom}" /></td>
                        <td><img height="100" width="100" src="image/trombi/<c:out value="${unePersonne.photo}"/>"/></td>
                        <td><c:out value="${unePersonne.no_tel}"/></td>
                        <td><c:out value="${unePersonne.email}"/></td>

                    </tr>  
                </c:forEach>
            </table>
        </div>

    </body>
</html>
