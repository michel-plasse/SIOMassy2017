<div class="col-md-12 qcm">
    <div class="col-md-10 text-center">
        <h2>Quizz : <c:out value="${requestScope.qcmPasse.intitule}" /></h2>
        <div class="col-md-3 pull-right"><c:out value="${requestScope.note}/100" /></div>
    </div>
    
    <div class="clearfix"></div>
    <br />
    <div class="col-md-10 col-md-offset-1">
        <c:forEach items="${requestScope.qcmPasse.lesQuestions}" var="q">
            <div class="panel panel-primary">
                <div class="panel-heading"><b>#Q - <c:out value="${q.question}" /></b></div>
                <div class="panel-body">
                    <c:forEach items="${q.lesChoix}" var="c" >
                        <input type="checkbox" name="${q.idQuestion}" id="${c.key}" value="${c.key}" <c:if test="${c.value.estChoisi}">checked</c:if> disabled />
                        <label for="${c.key}"><c:out value="${c.value.choix}" /></label>
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
            <a href="<c:url value="/" /> " class="btn btn-block btn-primary">Retour à la liste des QCM</a>
        </div>
    </div>
    <div class="clearfix"></div>
    <br />
</div>
    
<div class="clearfix"></div>