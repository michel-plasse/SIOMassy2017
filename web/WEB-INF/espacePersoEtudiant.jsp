
<p><img height="200" width="200" src="image/trombi/${user.photo}"/><br/>
    ${user.prenom} ${user.nom}</p>

<table class="table">	
    <tr>
        <th>Date</th>
        <th>Intitulé</th>
        <th>Module</th>
        <th>Formateur</th>
        <th>Note</th>
        <th>Commentaire</th>
    </tr>
    <c:forEach items="${requestScope.lesnotes}" var="note">
        <tr>
            <td><c:out value="${note.date_effet}" /></td>
            <td><c:out value="${note.intitule}" /></td>
            <td><c:out value="${note.nom}" /></td>
            <td><c:out value="${note.nom}" /></td>
            <td><c:out value="${note.note}" /></td>
            <td><c:out value="${note.commentaire}" /></td>

        </tr>  
    </c:forEach>
</table>

