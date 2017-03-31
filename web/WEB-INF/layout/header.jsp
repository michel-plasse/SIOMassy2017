<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agriote</title>
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/font-awesome/css/font-awesome.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" href="<c:url value="/inc/css/style.css" />">
    </head>

<body>

   <div class="container">
       
    <!--  #### CONTAINER #### -->
    
    
    <!--   BANNIERE -->
    <div id="banner-div">
        <div id="banner"></div>
    </div>
    <!--  FIN BANNIERE  -->
    
    <!--    BARRE MENU-->
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
          </div>
        </div>
    </nav>
    <!-- FIN BARRE MENU  -->
    
    <div id="main">
    <!-- #### DEBUT CONTENU PAGE #### -->
        