<%-- 
    Document   : login
    Created on : 14 mars 2017, 10:45:29
    Author     : nate
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<p:header titre="Sessions ouvertes à candidature"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page d'identification</title>
    </head>
    <body>
        <h1>Identifiez-vous</h1>
        <p><span>${inscriptionValide}</span></p>
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
                    <td>Mot de passe : </td>
                    <td>
                        <input type='password' name="password" required="required"/>
                        <span>${passwordMsg}</span>
                    </td>
                </tr>

                <tr>
                    <td><button type='submit'>Se connecter</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
