
<p><img height="200" width="200" src="image/trombi/${user.photo}"/><br/>
    ${user.prenom} ${user.nom}</p>

<table class="table">	
    <tr>
        <th>Date</th>
        <th>Module</th>
        <th>formateur</th>
        <th>Note</th>

    </tr>
    <c:forEach items="${lesNotes}" var="laNote">
        <tr>
            
            <td>${laNote.evaluation.dateDebutEval}</td>
            <td>${laNote.evaluation.leModule.nom}</td> 
            <td>${laNote.evaluation.leFormateur.nom} ${laNote.evaluation.leFormateur.prenom}</td>           
            <td>${laNote.note}</td>
      

        </tr>  
    </c:forEach>
</table>

