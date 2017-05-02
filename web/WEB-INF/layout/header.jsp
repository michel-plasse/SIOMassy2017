<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>
            Agriote<c:if test="${ !empty requestScope.title }"><c:out value=" - ${requestScope.title}" /></c:if>
            </title>

        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/font-awesome/css/font-awesome.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/css/style.css" />">
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value="/inc/css/print.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/offcanvas.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" />">
    </head>

    <body>

        <div class="container">
            <!--  #### CONTAINER #### -->


            <!--   BANNIERE -->
            <!--    <div id="banner-div">
                    <div id="banner"></div>
                </div>-->
            <!--  FIN BANNIERE  -->

            <!--    BARRE MENU-->
            <nav id="menu" class="navbar navbar-default">
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
                            <li class="item-menu"><a href="<c:url value="/"/>">Accueil</a></li>
                            <li class="item-menu"><a href="<c:url value="/sessionsOuvertes"/>">Formations</a></li>
                            <li class="item-menu"><a href="<c:url value="/trombinoscope"/>">Trombinoscope</a></li>
                            <li class="item-menu"><c:if test='${user.est_formateur eq false}'>
                                    <a href="<c:url value="/espacePersoEtudiant" />">Espace Personnel</a>
                                </c:if>
                                <c:if test="${user.est_formateur eq true}">
                                    <a href="<c:url value="/espacePersoFormateur" />">Espace Personnel</a>
                                </c:if></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><c:if test='${sessionScope.user == null}'>
                                    <a href="<c:url value="/login" />">Connexion</a>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <a href="<c:url value="/Deconnexion" />">Déconnexion</a>
                                </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- FIN BARRE MENU  -->

            <div id="main">
                <!-- #### DEBUT CONTENU PAGE #### -->

