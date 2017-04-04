
<p><img height="200" width="200" src="image/trombi/<c:out value="${user.photo}"/>"/><br>
    ${user.prenom} ${user.nom} 

    <table class="table">	
    <th>Date</th>
    <th>Intitulé</th>
    <th>Module</th>
    <th>Formateur</th>
    <th>Note</th>
    <th>Commentaire</th>

    <c:forEach items="${requestScope.eval}" var="uneEval">
        <tr>
            <td><c:out value="${eval.date_effet}" /></td>
            <td><c:out value="${eval.intitule}" /></td>
            <td><c:out value="${eval.nom}" /></td>
            <td><c:out value="${eval.nom}" /></td>
            <td><c:out value="${eval.note}" /></td>
            <td><c:out value="${eval.commentaire}" /></td>

        </tr>  
    </c:forEach>


</table>

</p>

