<div id="formateur-form">
    <div id="formateur-formBox">
        <img height="110" width="110" src="image/trombi/${user.photo}"/>
    </div>
    <section id="donnees-perso">
        <div id="donnees-lecture">
            <strong> ${user.prenom} ${user.nom}</strong><br>
            ${user.no_rue} ${user.rue}<br>
            ${user.code_postal} ${user.ville}<br>
            ${user.pays}<br>
            <i class="fa fa-phone" aria-hidden="true"></i> ${user.no_tel}<br>
            <i class="fa fa-envelope" aria-hidden="true"></i> <a href="mailto:${user.email}">${user.email} </a>
            <a href="javascript:$('#donnees-lecture').hide(); $('#donnees-ecriture').show()">Edit</a>
        </div>
            <div id="donnees-ecriture" style="display:none;">
             <table class="table">
                 <form class="form-horizontal">
                <tr> 
                    <td>
                        <label for="prenom">Prénom </label>
                        <input type="text" class="form-control" name="prenom" value="${user.prenom}">
                    </td>
                    <td>
                        <div class="form-group">
                            <Label for="nom">Nom </Label>
                            <input type="text" class="form-control" name="nom" value="${user.nom}">
                        </div>
                    </td> 

                </tr>
                <tr>
                    <td>
                        <label for="no_rue">Numéro</label>
                        <input type="text" class="form-control" size="3" maxlength="3" name="no_rue" value="${user.no_rue}">
                    </td>
                    <td>
                        <label for="nom_rue">Rue</label>
                        <input type="text" class="form-control" name="nom_rue" value="${user.rue}">
                    </td>
                    <td>
                        <label for="code_postal">Code Postal</label>
                        <input type="text" class="form-control" size="5" name="code_postal" value="${user.code_postal}">
                    </td>
                    <td>
                        <label>Ville</label>
                        <input type="text" class="form-control" name="ville" value="${user.ville}">

                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Pays</label>
                        <select class="form-control">
                            <option value="France" selected="selected">France</option>
                            <option value='OutreMer'>Outre-Mer</option>
                            <option value="AutreEurope">Autre pays (Europe)</option>
                            <option value="AutreAfrique">Autre pays (Afrique)</option>
                            <option value='AutreAsie'>Autre pays (Asie)</option>
                            <option value='AutreAmérique'>Autre pays (Amérique)</option>
                            <option value='AutreOcéanie'>Autre pays (Océanie)</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Téléphone</label>
                        <input type="text" class="form-control" name="no_phone" value="${user.no_tel}">
                    </td>
                    <td>
                        <label>E-Mail</label>
                        <input type="text" class="form-control" name="email" value="${user.email}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="valider" class="btn btn-default">Valider</button>
                    </td>
                </tr>

            </form>
        </table>
        </div>
    </section>
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
                <td><a href="espacePersoFormateur?id=${laNote.evaluation.leFormateur.id}">${laNote.evaluation.leFormateur.nom} ${laNote.evaluation.leFormateur.prenom}</a></td>           
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