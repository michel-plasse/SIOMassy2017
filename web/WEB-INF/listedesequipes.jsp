<div class="container-fluid">

        <div class="col-md-12">
            <div class="row-fluid">
                <h3><c:out value="${requestScope.title}" /></h3><br />
                  <c:forEach items="${requestScope.equipes}" var="uneEquipe" >
                      <c:if test="${uneEquipe.createur.id == sessionScope.user.id}">
                          <c:set var="statutStagiaire" value="1" scope="request" />
                      </c:if>
                      <div class="col-md-4">
                        <div class="list-group">
                            <span class="list-group-item active">Equipe de <c:out value="${uneEquipe.createur.prenom} ${uneEquipe.createur.nom}" /></span>
                            <c:forEach items="${uneEquipe.lesMembres}" var="membre">
                                <c:if test="${membre.value.id == sessionScope.user.id}">
                                    <c:set var="statutStagiaire" value="2" scope="request" />
                                </c:if>
                                <span class="list-group-item"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" /></span>
                            </c:forEach>
                        </div>
                    </div> 
                  </c:forEach>
            </div>


            <div class="clearfix section-grp"></div>   

            <div class="row-fluid">
                <h3><c:out value="Liste des stagiaires sans équipe" /></h3><br />  
                   <c:forEach items="${requestScope.stagiaires}" var="unStagiaire" >
                    <c:if test="${unStagiaire.id == sessionScope.user.id}">
                        <c:set var="statutStagiaire" value="3" scope="request" />
                    </c:if>
                    <div class="col-md-4">
                    <span class="list-group-item"><c:out value="${unStagiaire.nom}" /> <c:out value="${unStagiaire.prenom}" /></span>
                    </div> 
                   </c:forEach> 
            </div>
                   
            <div class="clearfix section-grp"></div>
            
            <div class="row-fluid">
                <c:choose>
                    <c:when test="${requestScope.statutStagiaire == 1}">
                        <a href="/">
                            <div class="alert alert-info">
                            Vous êtes le créateur d'une équipe,<strong> Accéder à sa gestion en cliquant ici</strong>
                            </div>
                        </a>
                    </c:when>
                    <c:when test="${requestScope.statutStagiaire == 2}">
                        <a href="/">
                            <div class="alert alert-info">
                            Vous êtes membre d'une équipe,<strong> Accéder au détail (coordonnées) en cliquant ici</strong>
                            </div>
                        </a>
                    </c:when>
                    <c:when test="${requestScope.statutStagiaire == 3}">
                        <a href="/">
                            <div class="alert alert-info">
                            Vous ne faites actuellement pas encore parti d'une équipe,<strong> N'attendez plus ! et créer la votre en cliquant ici</strong>
                            </div>
                        </a>
                    </c:when>
                    <c:otherwise>
                        vous n'avez rien à faire ici
                    </c:otherwise>
                </c:choose>
            </div>
            
        </div>
                   
</div>