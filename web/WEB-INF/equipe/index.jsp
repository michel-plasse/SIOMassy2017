<div class="col-md-12">
    <c:if test="${!empty sessionScope.messageOk}">
        <div class="alert alert-success"><c:out value="${sessionScope.messageOk}" /></div>
        <c:remove scope="session" var="messageOk" />
    </c:if>
    <c:if test="${!empty sessionScope.messageError}">
        <div class="alert alert-danger"><c:out value="${sessionScope.messageError}" /></div>
        <c:remove scope="session" var="messageError" />
    </c:if>
    <div class="row-fluid">
        <h3><c:out value="${requestScope.title}" /></h3><br />
        <c:choose>
            <c:when test="${empty requestScope.equipes}">
                -> Aucune équipe formée pour le moment...
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.equipes}" var="uneEquipe" >
                    <c:if test="${uneEquipe.createur.id == sessionScope.user.id}">
                        <c:set var="statutStagiaire" value="1" scope="page" />
                        <c:set var="idEquipe" value="${uneEquipe.id}" scope="page" />
                    </c:if>
                    <div class="col-md-4">
                        <div class="list-group">
                            <span class="list-group-item active">Equipe de <c:out value="${uneEquipe.createur.prenom} ${uneEquipe.createur.nom}" /></span>
                            <c:forEach items="${uneEquipe.lesMembres}" var="membre">
                                <c:if test="${membre.value.id == sessionScope.user.id}">
                                    <c:set var="statutStagiaire" value="2" scope="page" />
                                    <c:set var="idEquipe" value="${uneEquipe.id}" scope="page" />
                                </c:if>
                                <span class="list-group-item"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" /></span>
                            </c:forEach>
                        </div>
                    </div> 
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>


    <div class="clearfix section-grp"></div>   

    <div class="row-fluid">
        <h3><c:out value="Liste des stagiaires sans équipe" /></h3><br />
        <c:choose>
            <c:when test="${empty requestScope.stagiaires}">
                -> Aucun stagiaire sans équipe....
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.stagiaires}" var="unStagiaire" >
                    <c:if test="${unStagiaire.id == sessionScope.user.id}">
                        <c:set var="statutStagiaire" value="3" scope="page" />
                    </c:if>
                    <div class="col-md-4">
                        <span class="list-group-item"><c:out value="${unStagiaire.prenom}" /> <c:out value="${unStagiaire.nom}" /></span>
                    </div> 
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="clearfix section-grp"></div>

    <div class="row-fluid">
        <c:choose>
            <c:when test="${pageScope.statutStagiaire == 1}">
                <div class="col-md-6">
                    <a class="btn btn- btn-primary btn-block" href="<c:url value="/equipe/gerer"><c:param name="id" value="${pageScope.idEquipe}" /></c:url>">
                            Gérer votre équipe
                        </a>
                    </div>
                    <div class="col-md-6">
                        <a class="btn btn-block btn-success" href="<c:url value="/equipe/details"><c:param name="id" value="${pageScope.idEquipe}" /></c:url>">
                            Accéder aux détails des membres 
                        </a>
                    </div>
            </c:when>
            <c:when test="${pageScope.statutStagiaire == 2}">
                <div class="col-md-12">
                    <a class="btn btn-block btn-success" href="<c:url value="/equipe/details"><c:param name="id" value="${pageScope.idEquipe}" /></c:url>">
                            Vous êtes membre d'une équipe,<strong> Accéder aux détails (coordonnées) en cliquant ici</strong> 
                        </a>
                    </div>
            </c:when>
            <c:when test="${pageScope.statutStagiaire == 3}">
                <div class="col-md-12">
                    <a class="btn btn-block btn-primary" href="<c:url value="/equipe/creer"><c:param name="id" value="${requestScope.idProjet}" /></c:url>">
                            Vous ne faites actuellement pas encore parti d'une équipe,<strong> N'attendez plus ! et créer la votre en cliquant ici</strong>
                        </a>
                    </div>
            </c:when>
            <c:otherwise>
                <!-- Will see -->
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="clearfix"></div>