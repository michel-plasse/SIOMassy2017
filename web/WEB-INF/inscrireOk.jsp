<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription confirmée</title>
</head>
<body>
<p>Merci de vous être enregistré <span>${leNom } ${lePrenom }</span>.
Veuillez cliquez sur le lien afin de finaliser votre inscription.
<!--<a href="http://localhost/SIOMassy2017/confirmerinscription?token=${leToken}">Confirmer</a>-->
<a href="<c:url value="ConfirmerInscription"><c:param name="token" value="${requestScope.leToken}"/></c:url>">Confirmer</a> <!--(avec jstl)-->
</body>
</html>