<div id="formateur-form">
    <div id="formateur-formBox">
        <img height="100" width="100" src="image/trombi/${user.photo}"/>
    </div>
    <div id="formateur-formBox">
        <strong> ${user.prenom} ${user.nom}</strong><br>
                ${user.no_rue} ${user.rue}<br>
                ${user.code_postal} ${user.ville}<br>
                ${user.pays}<br>
                <i class="fa fa-phone" aria-hidden="true"></i> ${user.no_tel}<br>
                <i class="fa fa-envelope" aria-hidden="true"></i> ${user.email}
    </div>
</div>
<br>    

<a href="#demo" data-toggle="collapse">Mes Notes</a> </br>

<div id="demo" class="collapse">

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
</div>
    
<a href="#demo1" data-toggle="collapse">Mes Projets</a> 

<div id="demo1" class="collapse">
<table class="table">	
    
     <tr>
        <th>Nom Projet</th>
        <th>Date début</th>
        <th>Date limite</th>
        <th>Formateur</th>
 
    </tr>
    <c:forEach items="${lesProjets}" var="leProjet">
        <tr>
            
<!--            <td><a href="espacePersoFormateur">${leProjet.sujet}</a></td>
            <td>${leProjet.dateCreation}</td> 
            <td>${leProjet.dateLimite}</td>           
            <td><a href="espacePersoFormateur">${leProjet.leFormateur.nom} ${leProjet.leFormateur.prenom}</a></td>-->
        </tr>  
    </c:forEach>
</table>
</div>
</div>