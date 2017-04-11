
<p><img height="200" width="200" src="image/trombi/${user.photo}"/><br/>
    ${user.prenom} ${user.nom}</p>

<table class="table">	
    <tr>
        <th>Date</th>
        <th>Intitulé</th>
<!--        <th>Module</th>-->
<!--        <th>Formateur</th>
        <th>Note</th>
        <th>Commentaire</th>-->
    </tr>
    <c:forEach items="${requestScope.lesNotes}" var="laNote">
        <tr>
            <td><c:out value="${laNote.note}" /></td>
            <td><c:out value="${laNote.commentaire}" /></td>

        </tr>  
    </c:forEach>
</table>

