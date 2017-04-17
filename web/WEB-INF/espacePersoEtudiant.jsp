
<p><img height="200" width="200" src="image/trombi/${user.photo}"/><br/>
    ${user.prenom} ${user.nom}</p>

<table class="table">	
    <tr>
        <th>Date</th>
        <th>Intitulé</th>
        <th>Module</th>-->
<!--        <th>Formateur</th>
        <th>Note</th>
        <th>Commentaire</th>-->
    </tr>
    <c:forEach items="${lesNotes}" var="laNote">
        <tr>
            
            <td>${laNote.evaluation.dateDebutEval}</td>
            <td>${laNote.note}</td>
            <td>${laNote.commentaire}</td>

        </tr>  
    </c:forEach>
</table>

