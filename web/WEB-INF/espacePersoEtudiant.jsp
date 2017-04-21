
<p><img height="200" width="200" src="image/trombi/${user.photo}"/><br/>
<h1>${user.prenom} ${user.nom}</h1></p>

<table class="table">	
    <tr>
        <th>Date</th>
        <th>Module</th>
        <th>formateur</th>
        <th>Note</th>
        <th>Commentaire</th>

    </tr>
    <c:forEach items="${lesNotes}" var="laNote">
        <tr>
            
            <td>${laNote.evaluation.dateDebutEval}</td>
            <td>${laNote.evaluation.leModule.nom}</td> 
            <td><a href="espacePersoFormateur">${laNote.evaluation.leFormateur.nom} ${laNote.evaluation.leFormateur.prenom}</a></td>           
            <td>${laNote.note}</td>
            <td>${laNote.commentaire}</td>
        </tr>  
    </c:forEach>
</table>

<table class="table">	
     <tr>
        <th>Nom Projet</th>
        <th>Date début</th>
        <th>Date limite</th>
        <th>Formateur</th>
 
    </tr>
    <c:forEach items="${lesProjets}" var="leProjet">
        <tr>
            
<!--            <td><a href="espacePersoFormateur">${leProjet.nom}</a></td>
            <td>${leProjet.dateDebut}</td> 
            <td>${leProjet.dateLimite}</td>           
            <td><a href="espacePersoFormateur">${leProjet.leFormateur.nom} ${leProjet.leFormateur.prenom}</a></td>-->
        </tr>  
    </c:forEach>
</table>