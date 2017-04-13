<div class="col-md-12 qcm">
    <div class="col-md-12 text-center"><h2>Quizz : <c:out value="${requestScope.qcm.intitule}" /></h2></div>
    
    <div class="clearfix"></div>
    <br />
    <div class="col-md-10 col-md-offset-1">
        <div class="alert alert-info">
            <b>Rappel des règles de notation :</b>
            une mauvaise réponse entraine le retrait d'un point, l'absence de réponse n'ôte pas de point.
        </div>

        <form action="<c:url value="/passerqcm" />" method="POST">
        <c:forEach items="${requestScope.qcm.lesQuestions}" var="q">
            <div class="panel panel-primary">
                <div class="panel-heading"><b>#Q - <c:out value="${q.question}" /></b></div>
                <div class="panel-body">
                    <c:forEach items="${q.lesChoix}" var="c" >
                        <input type="checkbox" name="${q.idQuestion}" id="${c.key}" value="${c.key}" /><label for="${c.key}"><c:out value="${c.value.choix}" /></label><br />
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
        <div class="alert alert-warning">Attention, vous ne pouvez passer ce Quizz qu'une seule fois. Vos réponses ne pourrons pas être modifiées après validation.</div>
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