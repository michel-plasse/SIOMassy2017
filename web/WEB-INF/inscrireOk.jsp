<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription confirmée</title>
</head>
<body>
<p>Merci de vous être enregistré <span>${leNom } ${lePrenom }</span>.
Veuillez cliquez sur le lien afin de finaliser votre inscription.
<a href='http://localhost/SIOMassy2017/confirmerInscription?token="+token+"'></a>
</body>
</html>