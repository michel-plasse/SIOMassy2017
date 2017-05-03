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
            <br>
            <i class="fa fa-pencil-square-o" aria-hidden="true"></i><a href="javascript:$('#donnees-lecture').hide(); $('#donnees-ecriture').show()">Editer mes informations</a>
        </div>
        <div id="donnees-ecriture" style="display:none;">
            <table class="table">
                <form class="form-horizontal" method="post">
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
                            <select class="form-control" name='pays'>
                                <c:forEach items="${lesPays}" var="pays">
                                    <c:set var="selected" value=""/>
                                    <c:if test="${pays eq user.pays}">
                                        <c:set var="selected" value="selected"/>
                                    </c:if>
                                    <option value="${pays}" ${selected}>${pays}</option>
                                </c:forEach>
                                <!--                            <option value="France" >France</option>
                                                            <option value='OutreMer'>Outre-Mer</option>
                                                            <option value="AutreEurope">Autre pays (Europe)</option>
                                                            <option value="AutreAfrique">Autre pays (Afrique)</option>
                                                            <option value='AutreAsie'>Autre pays (Asie)</option>
                                                            <option value='AutreAmérique'>Autre pays (Amérique)</option>
                                                            <option value='AutreOcéanie'>Autre pays (Océanie)</option>-->
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

<!--<a href="#demo" data-toggle="collapse">Mes Notes</a> </br>

 <div id="demo" class="collapse"> -->
<div id="donnees-lecture-note">
    <table class="table">	
        <tr>
            <th>Date</th>
            <th>Module</th>
            <th>formateur</th>
            <th>Note</th>
            <th>Commentaire</th>

        </tr>
        <c:forEach items="${lesNotes}" var="laNote" end="1">
            <tr>

                <td>${laNote.evaluation.dateDebutEval}</td>
                <td>${laNote.evaluation.leModule.nom}</td> 
                <td>${laNote.evaluation.leFormateur.nom} ${laNote.evaluation.leFormateur.prenom}</td>           
                <td>${laNote.note}</td>
                <td>${laNote.commentaire}</td>
            </tr>  
        </c:forEach>
    </table>    
</div>

<div id="donnees-ecriture-note" style="display:none;">
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
                <td>${laNote.evaluation.leFormateur.nom} ${laNote.evaluation.leFormateur.prenom}</td>           
                <td>${laNote.note}</td>
                <td>${laNote.commentaire}</td>
            </tr>  
        </c:forEach>
    </table>
</div>

<a href="javascript:$('#donnees-lecture-note').hide(); $('#donnees-ecriture-note').fadeIn('slow').show()">Afficher toutes mes notes...</a> 
<a href="javascript:$('#donnees-ecriture-note').hide(); $('#donnees-lecture-note').fadeIn('slow').show()">Réduire</a> <br>
<br>

<div id="donnees-lecture-projet">
    <table class="table">	

        <tr>
            <th>Nom Projet</th>
            <th>Date début</th>
            <th>Date limite</th>
            <th>Formateur</th>
            <th>Equipes</th>

        </tr>
        <c:forEach items="${lesProjets}" var="leProjet" end="1">
            <tr>

                <td>${leProjet.sujet}</td>
                <td>${leProjet.dateCreation}</td> 
                <td>${leProjet.dateLimite}</td>           
                <td>${leProjet.nomFormateur} ${leProjet.prenomFormateur}</td>
                <td>
                    <a href="<c:url value="/equipe/index"><c:param name="id" value="${leProjet.id}" /></c:url>" title="Voir les équipes">
                            <i class="fa fa-users" aria-hidden="true"> Voir les équipes</i>
                        </a>
                    </td>
                </tr>  
        </c:forEach>
    </table>
</div>

<div id="donnees-ecriture-projet" style="display:none;">
    <table class="table">	

        <tr>
            <th>Nom Projet</th>
            <th>Date début</th>
            <th>Date limite</th>
            <th>Formateur</th>

        </tr>
        <c:forEach items="${lesProjets}" var="leProjet">
            <tr>

                <td>${leProjet.sujet}</td>
                <td>${leProjet.dateCreation}</td> 
                <td>${leProjet.dateLimite}</td>           
                <td>${leProjet.nomFormateur} ${leProjet.prenomFormateur}</td>
            </tr>  
        </c:forEach>
    </table>
</div>

<a href="javascript:$('#donnees-lecture-projet').hide(); $('#donnees-ecriture-projet').fadeIn('slow').show()">Afficher tous mes projets..</a> 
<a href="javascript:$('#donnees-ecriture-projet').hide(); $('#donnees-lecture-projet').fadeIn('slow').show()">Réduire</a> <br> 

<br>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#qcmpasse" aria-controls="home" role="tab" data-toggle="tab">Qcm Passé</a></li>
        <li role="presentation"><a href="#qcmnonpasse" aria-controls="profile" role="tab" data-toggle="tab">Qcm non Passé</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <table role="tabpanel" class="tab-pane active table" id="qcmpasse">

            <tr>

                <th>Module</th>
                <th>Nom</th>

            </tr>
            <c:forEach items="${LesQcmPasse}" var="leqcm" >
                <tr>

                    <td>${leqcm.nomModule}</td>
                    <td>${leqcm.intitule}</td>
                    <td><a href="<c:url value="/qcm/passer"><c:param name="idQcmPasser" value="${leqcm.idQcm}"/></c:url>">Consulter</a></td>

                    </tr>
            </c:forEach>

        </table>
        <table role="tabpanel" class="tab-pane  table" id="qcmnonpasse">

            <tr>

                <th>Module</th>
                <th>Nom</th>
                <th></th>

            </tr>
            <c:forEach items="${lesQcmNonPasse}" var="leqcm">
                <tr>

                    <td>${leqcm.nomModule}</td>
                    <td>${leqcm.intitule}</td>
                    <td><a href="<c:url value="/qcm/passer"><c:param name="idQcmPasser" value="${leqcm.idQcm}"/></c:url>">Passer</a></td>

                    </tr>
            </c:forEach>

        </table>
    </div>
</div>
