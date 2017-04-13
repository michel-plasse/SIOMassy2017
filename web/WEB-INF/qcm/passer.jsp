<div class="col-md-12 qcm">
    <div class="col-md-12 text-center"><h2>Quizz : <c:out value="${requestScope.qcm.intitule}" /></h2></div>
    
    <div class="clearfix"></div>
    <br />
    <div class="col-md-10 col-md-offset-1">
        <div class="alert alert-info">
            <i class="fa fa-info-circle" aria-hidden="true"></i>
            <b>Rappel des règles de notation :</b>
            une mauvaise réponse entraine le retrait d'un point, l'absence de réponse n'ôte pas de point.
        </div>

        <form action="<c:url value="/qcm/passer"><c:param name="id" value="${requestScope.qcm.idQcm}" /></c:url>" method="POST">
        <c:forEach items="${requestScope.qcm.lesQuestions}" var="q">
            <div class="panel panel-primary">
                <div class="panel-heading">
                        <i class="fa fa-question-circle-o" aria-hidden="true"></i>
                        <b> <c:out value="${q.question}" /></b></div>
                <div class="panel-body">
                    <c:forEach items="${q.lesChoix}" var="c" >
                        <input type="checkbox" name="${q.idQuestion}" id="${c.key}" value="${c.key}" /><label for="${c.key}"><c:out value="${c.value.choix}" /></label><br />
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="alert alert-warning">
            <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
            Attention, vous ne pouvez passer ce Quizz qu'une seule fois. Vos réponses ne pourrons pas être modifiées après validation.
        </div>
        <div class="col-md-6 pull-right">
            <div class="col-md-6">
            <button class="btn btn-block btn-primary" type="reset" name="reset">Réinitialiser</button>
            </div>
            <div class="col-md-6">
            <button class="btn btn-block btn-primary" type="submit" name="valider" value="1">Valider</button>
            </div>
        </div>
        <input type="hidden" name="idQcm" value="${requestScope.qcm.idQcm}" />
        </form>
    </div>
    <div class="clearfix"></div>
    <br />
</div>
    
<div class="clearfix"></div>