<div class="jumbotron">
    <div class="col-md-12 text-center"><h2>Quizz : <c:out value="${requestScope.qcm.intitule}" /></h2></div>
    
    <div class="clearfix"></div>
    <br />
    <c:forEach items="${requestScope.qcm.lesQuestions}" var="q">
        <div class="panel panel-info">
            <div class="panel-heading">#Q - <c:out value="${q.question}" /></div>
            <div class="panel-body">
                <c:forEach items="${q.lesChoix}" var="c" >
                    <input type="radio" name="${q.idQuestion}" id="${c.idChoix}" value="${c.idChoix}" /><label for="${c.idChoix}"> -  <c:out value="${c.choix}" /></label><br />
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    
</div>