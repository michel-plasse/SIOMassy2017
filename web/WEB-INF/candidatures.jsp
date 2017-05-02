
        <form action="">
            <div class="row form-group">
                <div class="col-xs-8 col-sm-6 col-md-6">
                    <div class="input-group">
                        <input type="search" class="form-control" placeholder="Rechercher candidatures"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-search"></i>
                        </span>
                    </div>
                </div>
                <div class="col-xs-4 col-sm-6 col-md-6">
                    <div class="col-xs-4 col-sm-4 col-md-4">
                        <select name="statut" class="form-control">
                            <option value="" selected="">Etat</option>
                        <c:forEach var="unStatut" items="${requestScope.lesEtats}">                
                            <option value="<c:out value="${unStatut.libelle}" />"><c:out value="${unStatut.libelle}" /></option>                           
                        </c:forEach> 
                        </select>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4">
                        <select name="formationNom" class="col-xs-4 col-sm-4 col-md-4 form-control">
                            <option value="" selected="">Formation</option>
                        <c:forEach var="unFormation" items="${requestScope.lesFormations}">                
                            <option value="<c:out value="${unFormation.nom}" />"><c:out value="${unFormation.nom}" /></option>                           
                        </c:forEach>                            
                         </select>
                    </div>
                    <div class="col-xs-4 col-sm-4 col-md-4">
                        <div class="input-group date">
                            <input type="text" class="form-control" placeholder="jj/mm/aaaa">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </form>
           
        <table class="table table-striped table-hover">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Statut</th>
                <th>Session</th>
                <th>Date_de_postulation</th>
            </tr>
        <c:forEach var="unCandidature" items="${lesCandidatures}">
            <tr class='clickable-row' data-href='<c:url value="/candidature?personne=${unCandidature.idPersonne}&session=${unCandidature.idSession}&etat=${unCandidature.idEtatCandidature}" />'>
                <td><c:out value="${unCandidature.nom}"/></td>
                <td><c:out value="${unCandidature.prenom}"/></td>
                <td><c:out value="${unCandidature.statut}"/></td>
                <td><c:out value="${unCandidature.formationNom}"/></td>
                <td><c:out value="${unCandidature.effectue}"/></td>
            </tr>            
        </c:forEach>
        </table>
