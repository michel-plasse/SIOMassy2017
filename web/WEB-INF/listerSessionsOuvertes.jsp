<%@page import="model.SessionFormation" %>
<%@page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<jsp:useBean id="lesSessions" class="java.util.ArrayList" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des sessions ouvertes</title>
    </head>

    <body>
        <h1>Liste des formations ouvertes</h1>

        <table border="1">	
            <th>identifiant de la session</th>
            <th>nom de la formation</th>
            <th>descriptif de la formation</th>
            <th>début de la formation</th>
            <th>fin de la formation</th>
            <th>lieu de la formation</th>
            <th>Lien vers la formation</th>

            <%for (SessionFormation a : (ArrayList<SessionFormation>) lesSessions) {
                        out.println("<tr><td>" + a.getId_session() + "</td> <td>" + a.getNom() + "</td> <td>" + a.getDescriptif() + "</td> <td>" + a.getDateDebut()
                                + "</td> <td>" + a.getDateFin() + "</td> <td>" + a.getLieu() + "</td></tr>");
                    }%>
        </table>

        Nombre de sessions ouvertes = <%= lesSessions.size()%>

    </body>
</html>