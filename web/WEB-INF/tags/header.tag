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
        <a href="<c:url value="/"/>">Acceuil</a>
        <a href="<c:url value="sessionsOuvertes"/>">Formation</a>
        <a href="<c:url value="Trombinoscope"/>">Trombinoscope</a>
        <a href="<c:url value="/"/>">Espace Personnel</a>
        <form id="connexion">
            <c:set var="user" value="${sessionScope['user']}"/>
            <c:if test="${user == null}">
                        <form method="post" action="login">
            <table border =' 0'>
                <tr>
                    <td>Identifiant : </td>
                    <td>
                        <input type='text' name="login" required="required"/>
                        <span>${loginMsg}</span>
                    </td>    
                </tr>

                <tr>
                    <td>Mot de passe : <input type='password' name="password" required="required"/>
                        <span>${passwordMsg}</span>
<button type='submit'>Se connecter</button>
        </form>
            </c:if>
            <c:if test="${user != null}">
                <form action="login" method="post">
                    <a href="<c:url value="Deconnexion"/>"><button type="submit">Déconnecter ${user.nom} ${user.prenom}</button></a>
                </form>
            </c:if>
        </form>
    </nav>
    <h1>${titre}</h1>
