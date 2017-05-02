
<div class="col-md-10 col-md-offset-1">
    <h3><c:out value="${requestScope.title}" /></h3>
    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
    <div class="list-group">
        <span class="list-group-item">
            <img class="img-membre" src="../../image/trombi/<c:out value="${equipe.createur.photo}" />" />
            <c:out value="${equipe.createur.prenom}" /> <c:out value="${equipe.createur.nom}" /> <i>( créateur )</i>
            &nbsp; / &nbsp;
            <i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${equipe.createur.no_tel}" />
            &nbsp; / &nbsp;
            <i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${equipe.createur.email}" />
        </span>
        <c:forEach items="${requestScope.equipe.lesMembres}" var="membre">
            <span class="list-group-item">
                <img class="img-membre" src="../../image/trombi/<c:out value="${membre.value.photo}" />" />
                <c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" />
                &nbsp; / &nbsp;
                <i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${membre.value.no_tel}" />
                &nbsp; / &nbsp;
                <i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${membre.value.email}" />
            </span>
        </c:forEach>
    </div>
    <div class="col-md-4 col-md-offset-4">
            <a class="btn btn-block btn-primary" href="<c:url value="/etudiant/equipe/index"><c:param name="id" value="${equipe.idProjet}" /></c:url>">
                Retour à la liste des équipes
            </a>
    </div>
</div>
<div class="clearfix"></div>
