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
        <a href="<c:url value="trombinoscope"/>">Trombinoscope</a>
        <a href="<c:url value="/"/>">Espace Personnel</a>
        <c:set var="user" value="${sessionScope['user']}"/>
        <c:if test="${user == null}">
            <form id="connexion" method="post" action="login">
                Identifiant :
                <input type='text' name="login" required="required"/>
                <span>${loginMsg}</span>
                Mot de passe : <input type='password' name="password" required="required"/>
                <span>${passwordMsg}</span>
                <input type="submit" value="Se connecter"/>
            </form>
        </c:if>
        <c:if test="${user != null}">
            <a href="<c:url value="Deconnexion"/>"><button>Déconnecter ${user.nom} ${user.prenom}</button></a>
        </c:if>
    </form>
</nav>
<h1>${titre}</h1>
