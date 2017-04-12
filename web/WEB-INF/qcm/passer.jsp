<div class="col-md-12 qcm">
    <div class="col-md-12 text-center"><h2>Quizz : <c:out value="${requestScope.qcm.intitule}" /></h2></div>
    
    <div class="clearfix"></div>
    <br />
    <form action="<c:url value="/passerqcm" />" method="POST">
    <div class="col-md-10 col-md-offset-1">
        <c:forEach items="${requestScope.qcm.lesQuestions}" var="q">
            <div class="panel panel-primary">
                <div class="panel-heading"><b>#Q - <c:out value="${q.question}" /></b></div>
                <div class="panel-body">
                    <c:forEach items="${q.lesChoix}" var="c" >
                        <input type="checkbox" name="${q.idQuestion}" id="${c.idChoix}" value="${c.idChoix}" /><label for="${c.idChoix}"><c:out value="${c.choix}" /></label><br />
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="col-md-6 pull-right">
            <div class="col-md-6">
            <button class="btn btn-block btn-primary" type="reset" name="reset">Réinitialiser</button>
            </div>
            <div class="col-md-6">
            <button class="btn btn-block btn-primary" type="submit" name="valider" value="1">Valider</button>
            </div>
        </div>
    </div>
    </form>
    <div class="clearfix"></div>
    <br />
</div>
    
<div class="clearfix"></div>