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
                </div>                
            </section>
        </div>
        <br>
        <div class="panel panel-default">

            <div class="panel-heading" style="text-align: center">Derniers Candidatures</div>
            <table class="table table-striped table-hover">
                <tr>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>Etat</th>
                    <th>Formation</th>
                    <th>Postulé</th>
                </tr>
            <c:forEach var="unCandidature" items="${lesCandidatures}">
                <tr class='clickable-row' data-href='<c:url value="/candidature?personne=${unCandidature.idPersonne}&session=${unCandidature.idSession}&formation=${unCandidature.idFormation}&etat=${unCandidature.idEtatCandidature}" />'>
                    <td><c:out value="${unCandidature.nom}"/></td>
                    <td><c:out value="${unCandidature.prenom}"/></td>
                    <td><c:out value="${unCandidature.etat}"/></td>
                    <td><c:out value="${unCandidature.formationNom}"/></td>
                    <td><c:out value="${unCandidature.effectue}"/></td>
                </tr>            
            </c:forEach>
            </table>
        </div>
        <a href="<c:url value="/candidatures"/>">Plus</a>
