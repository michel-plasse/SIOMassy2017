<%@ tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="titre" description="Utilisé dans title et h1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${titre}</title>
    </head>

    <body>
    <nav class="topnav">
        Menu commun à toutes les pages
        <form id="connexion">
            <c:set var="user" value="${sessionScope['user']}"/>
            <c:if test="${user == null}">
                formulaire de connexion
            </c:if>
            <c:if test="${user != null}">
                <form action="login" method="post">
                    <button type="submit">Déconnecter ${user.email}</button>
                </form>
            </c:if>
        </form>
    </nav>
    <h1>${titre}</h1>
