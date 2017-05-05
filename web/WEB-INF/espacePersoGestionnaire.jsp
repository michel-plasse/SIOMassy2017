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
                    <a id="editInfos" style="cursor: pointer">Edit</a>
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
        <h1 style="text-align: center">Derniers Candidatures</h1>
        <table class="table table-striped table-hover">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Etat</th>
                <th>Session</th>
                <th>Date postulé</th>
            </tr>
        <c:forEach var="unCandidature" items="${lesCandidatures}">
            <tr class='clickable-row' data-href='<c:url value="/candidature?personne=${unCandidature.idPersonne}&session=${unCandidature.idSession}&etat=${unCandidature.idEtatCandidature}" />'>
                <td><c:out value="${unCandidature.nom}"/></td>
                <td><c:out value="${unCandidature.prenom}"/></td>
                <td><c:out value="${unCandidature.etat}"/></td>
                <td><c:out value="${unCandidature.formationNom}"/></td>
                <td><c:out value="${unCandidature.effectue}"/></td>
            </tr>            
        </c:forEach>
        </table>
        <a href="<c:url value="/candidatures"/>">Plus</a>
