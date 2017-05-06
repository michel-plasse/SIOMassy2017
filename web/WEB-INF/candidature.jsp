<div class="row">
    <div class="col-xs-12 col-sm-6 col-md-6">
        <h3 style="text-align: center">Informations du candidat</h3>
        <div class="col-xs-4 col-sm-4 col-md-4">
            <img height="110" width="110" src="image/trombi/${personne.photo}"/>
        </div>
        <section class="col-xs-8 col-sm-8 col-md-8"> 

            <div id="donnees-lecture">
                <strong> ${personne.prenom} ${user.nom}</strong><br>
                ${personne.no_rue} ${user.rue}<br>
                ${personne.code_postal} ${user.ville}<br>
                ${personne.pays}<br>
                <i class="fa fa-phone" aria-hidden="true"></i> ${personne.no_tel}<br>
                <i class="fa fa-envelope" aria-hidden="true"></i> <a href="mailto:${personne.email}">${personne.email} </a>
            </div>                
        </section>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-6">
        <h3 style="text-align: center">Informations de la candidature</h3>
        <dl class="dl-horizontal" id="infoCandidature">
            <dt>Etat de la candidature:</dt>
            <dd>${etat.libelle} <a href="#" id="modifierEtat">modifier</a></dd>
            <dt>Postulé:</dt>
            <dd><c:forEach var="candidature" items="${unCandidature}"><c:out value="${candidature.effectue}"/></c:forEach></dd>
            <dt>Formation:</dt>
            <dd>${formation.nom} (${formation.description})</dd>
            <dt>Durée:</dt>
            <dd>De ${session.dateDebut} à ${session.dateFin}</dd>
        </dl>
        <div id="etatForm" style="display: none">
            <form method="POST">
                <select name="etat" class="form-control">
                    <c:forEach var="unEtat" items="${requestScope.lesEtats}">                
                        <option value="<c:out value="${unEtat.idEtatCandidature}" />" <c:if test="${param.etat == unEtat.idEtatCandidature}"> selected</c:if> ><c:out value="${unEtat.libelle}" /></option>                         
                    </c:forEach> 
                </select>
                <button type="submit" class="btn btn-primary">Valider</button> 
                <button id="closeEtatForm" type="button" class="btn btn-default">Annuler</button>
            </form>
        </div>        
    </div>
</div>
