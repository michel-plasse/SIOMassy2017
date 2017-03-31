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

    </head>
    <body>

        <div class="col-md-2">
            <ul class = "nav nav-pills nav-stacked">
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


<div class="container">
	<div class="row">
		
                    <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
                        <div class="col-md-3 col-sm-4 col-xs-6">
                            <c:out value="${unePersonne.nom}" />
                            <c:out value="${unePersonne.prenom}" /><br>
                            <img height="100" width="100" src="image/trombi/<c:out value="${unePersonne.photo}"/>"/><br>
                            <c:out value="${unePersonne.no_tel}"/><br>
                            <c:out value="${unePersonne.email}"/>
                        </div>
                    </c:forEach>
            </div>
        </div>
    </body>
</html>
