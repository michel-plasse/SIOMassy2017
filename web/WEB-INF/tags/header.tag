<%@ tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="titre" description="Utilisé dans title et h1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${titre}</title>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/css/style.css" />">
    </head>

    <body>
   <div class="container">
       <br>
    <div id="banner-div">
        <div id="banner"></div>
   </div>
       
    <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li><a href="<c:url value="/"/>">Accueil</a></li>
          <li><a href="<c:url value="sessionsOuvertes"/>">Formations</a></li>
          <li><a href="<c:url value="trombinoscope"/>">Trombinoscope</a></li>
          <li><a href="<c:url value="/"/>">Nous Contacter</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="<c:url value="login"/>">Espace Personnel</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div><!--/.container-fluid -->
  </nav>

          
<%--<c:set var="user" value="${sessionScope['user']}"/>
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
--%>



<h1>${titre}</h1>
