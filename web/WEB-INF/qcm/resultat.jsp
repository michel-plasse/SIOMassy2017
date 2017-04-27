<div class="col-md-12 qcm">
    <div class="col-md-10 text-center col-md-offset-1">
        <h2>Quizz : <c:out value="${requestScope.qcmPasse.intitule}" /></h2>
    </div>
    <div class="clearfix"></div>
    <br />
    <div class="col-md-10 col-md-offset-1">
        <div class="alert alert-info">
            <i class="fa fa-info-circle" aria-hidden="true"></i>
            <b>Rappel des règles de notation :</b>
            une mauvaise réponse ou l'absence de réponse n'ôte pas de point.
        </div>
        <div class="clearfix"></div>
        
        <div class="col-md-3 label label-danger pull-right">
            <h2><c:out value="Score : ${requestScope.note}%" /></h2>
        </div>
        <c:forEach items="${requestScope.qcmPasse.lesQuestions}" var="q">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="fa fa-question-circle-o" aria-hidden="true"></i>
                    <b> <c:out value="${q.question}" /></b></div>
                <div class="panel-body">
                    <c:forEach items="${q.lesChoix}" var="c" >
                        <input type="radio" name="${q.idQuestion}" id="${c.key}" value="${c.key}" <c:if test="${c.value.estChoisi}">checked</c:if> disabled />
                        <label for="${c.key}"><c:out value="${c.value.libelle}" /></label>
                        <c:choose>
                            <c:when test="${c.value.estCorrect}"><i class="fa fa-check fa-2x green" aria-hidden="true"></i></c:when>
                            <c:when test="${!c.value.estCorrect && c.value.estChoisi}"><i class="fa fa-times fa-2x red" aria-hidden="true"></i></c:when>
                        </c:choose>
                        <br />
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

        <div class="col-md-8 col-md-offset-2">
            <a href="<c:url value="/espacePersoEtudiant" /> " class="btn btn-block btn-primary">Retour</a>
        </div>
    </div>
    <div class="clearfix"></div>
    <br />
</div>
    
<div class="clearfix"></div>