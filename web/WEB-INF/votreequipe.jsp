<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-8">
            <br />
            <div class="list-group">
                <span class="list-group-item active">Votre Equipe</span>
                <span class="list-group-item">
                    <div>
                        <div style="display: inline;"><img class="img-membre" src="image/trombi/<c:out value="${equipe.createur.photo}" />" /></div>
                        <div style="display: inline;"><c:out value="${equipe.createur.prenom}" /> <c:out value="${equipe.createur.nom}" /> <i>( créateur )</i>
                            &nbsp; / &nbsp;
                            <i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${equipe.createur.no_tel}" />
                            &nbsp; / &nbsp;
                            <i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${equipe.createur.email}" />
                        </div>
                    </div>
                </span>
                <c:forEach items="${requestScope.equipe.lesMembres}" var="membre">
                    <span class="list-group-item">
                        <div>
                            <div style="display: inline;"><img class="img-membre" src="image/trombi/<c:out value="${membre.value.photo}" />" /></div>
                            <div style="display: inline;"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" />
                                &nbsp; / &nbsp;
                                <i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${membre.value.no_tel}" />
                                &nbsp; / &nbsp;
                                <i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${membre.value.email}" />
                            </div>
                        </div>
                    </span>
                </c:forEach>
            </div>
        </div>
    </div>           
</div>