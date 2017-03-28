<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Sessions ouvertes à candidature"/>
<table border="1">	
    <th>identifiant de la session</th>
    <th>nom de la formation</th>
    <th>descriptif de la formation</th>
    <th>début de la formation</th>
    <th>fin de la formation</th>
    <th>lieu de la formation</th>
    <th>Lien vers la formation</th>

    <c:forEach items="${requestScope.lesSessions}" var="uneSession">
        <tr>
            <td><c:out value="${uneSession.id_session}" /></td>
            <td><c:out value="${uneSession.nom}" /></td>
            <td><c:out value="${uneSession.descriptif}" /></td>
            <td><c:out value="${uneSession.dateDebut}" /></td>
            <td><c:out value="${uneSession.dateFin}" /></td>
            <td><c:out value="${uneSession.lieu}" /></td>
            <td><a href="<c:url value="postuler">
                       <c:param name="idSessionFormation" value="${uneSession.id_session}"/>
                   </c:url>">Cliquer ici</a></td>
        </tr>  
    </c:forEach>


</table>

Nombre de sessions ouvertes = <c:out value="${lesSessions.size()}" />

</body>
</html>


<%-- <a href="<c:url value="/monSiteWeb/countZeros.jsp">
        <c:param name="idSessionFormation" value="${lesSessions.id_session}"/>
   </c:url>">plop</a>--%>