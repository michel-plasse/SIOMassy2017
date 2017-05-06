
        <form action="" id="candidatures">
            <div class="row form-group">
                <div class="col-xs-8 col-sm-2 col-md-2">
                    <div class="input-group">
                        <input type="search" name="recherche" id="recherche" <c:if test="${param.recherche != null}"> value="${param.recherche}"</c:if> class="form-control" placeholder="nom ou prenom"/>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-search"></i>
                        </span>
                    </div>
                </div>
                <div class="col-xs-4 col-sm-10 col-md-10">
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <select name="etat" class="form-control">
                            <option value="" selected>Toutes les Etat</option>
                        <c:forEach var="unEtat" items="${requestScope.lesEtats}">                
                            <option value="<c:out value="${unEtat.idEtatCandidature}" />" <c:if test="${param.etat == unEtat.idEtatCandidature}"> selected</c:if> ><c:out value="${unEtat.libelle}" /></option>                         
                        </c:forEach> 
                        </select>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <select name="formation" class="col-xs-4 col-sm-4 col-md-4 form-control">
                            <option value="" selected>Les Formations</option>
                        <c:forEach var="unFormation" items="${requestScope.lesFormations}">                
                            <option value="<c:out value="${unFormation.id}" />" <c:if test="${param.formation == unFormation.id}"> selected</c:if> ><c:out value="${unFormation.nom}" /></option>                           
                        </c:forEach>                            
                         </select>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <div class="input-group date">
                            <input type="text" name="date" <c:if test="${param.date != null}"> value="${param.date}"</c:if> class="form-control" placeholder="jj/mm/aaaa">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3">
                        <span class="col-xs-3 col-sm-3 col-md-3" >Trier par:</span>
                        <div class="col-xs-9 col-sm-9 col-md-9">
                            <select name="trier" class="form-control" id="trier" >
                                <option value="">Date</option>
                                <option value="nom" <c:if test="${param.trier == 'nom'}"> selected</c:if> >Nom</option>
                                <option value="prenom" <c:if test="${param.trier == 'prenom'}"> selected</c:if> >Prenom</option>
                                <option value="etat" <c:if test="${param.trier == 'etat'}"> selected</c:if> >Etat</option>
                                <option value="formation" <c:if test="${param.trier == 'formation'}"> selected</c:if> >Formation</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </form>
           
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
