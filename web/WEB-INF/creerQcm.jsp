<%-- 
    Document   : creerQcm
    Created on : 4 avr. 2017, 12:58:01
    Author     : admin
--%>
<div>${message}</div>
<h3>${qcm.intitule}</h3>
<c:if test="${not empty qcm.lesQuestions}">
    <div class=" container">
        <form method="post">
            <c:forEach items="${qcm.lesQuestions}" var="laQuestion">
                <div class="col-md-8 col-md-offset-1">
                    <div>
                        <h4>${laQuestion.question}</h4>
                    </div><ul>
                        <c:forEach items="${laQuestion.lesChoix}" var="leChoix">
                            <li style="list-style-type:none;" <c:choose>
                                    <c:when test="${leChoix.value.estCorrect}"> class="bg-success"</c:when>
                                    <c:when test="${!leChoix.value.estCorrect}">class="bg-danger"</c:when>
                                </c:choose>>
                                ${leChoix.value.libelle}
                            </li>
                        </c:forEach>
                    </ul></div>
                <div class="col-md-2">
                    <c:if test="${!qcm.valide}">
                        <button type="submit" class="btn btn-danger" value="${laQuestion.idQuestion}" name="supprimerQuestion">Supprimer la question</button>
                    </c:if>
                </div>
            </c:forEach>
        </form>
    </div>
</c:if>
<c:if test="${!qcm.valide}">
    <form  method="post" class="form-horizontal" style="border-top-style: ridge">
        <label for="nom" style="padding-top: 10px">Entrez la question :</label><br />
        <textarea name="question" id="question" cols="100" class="form-control" style="resize: none" required></textarea><br>
        <div class="form-group">
            <label for="reponse1" class="col-sm-2 control-label">Réponse 1 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse1" id="reponse1" class="form-control"/>
                <div data-toggle="buttons" style="padding-top: 10px">
                    <label class="btn btn-primary"><input type="radio" name="valide" value="1" required /> est la bonne reponse<br></label>
                </div>
            </div>

        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 2 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse2" id="reponse" class="form-control"/>
                <div data-toggle="buttons" style="padding-top: 10px">
                    <label class="btn btn-primary"><input type="radio" name="valide" value="2" required /> est la bonne reponse<br></label>
                </div>
            </div>

        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 3 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse3" id="reponse" class="form-control"/>
                <div data-toggle="buttons" style="padding-top: 10px">
                    <label class="btn btn-primary"><input type="radio" name="valide" value="3" required /> est la bonne reponse<br></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 4 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse4" id="reponse" class="form-control"/>
                <div data-toggle="buttons" style="padding-top: 10px">
                    <label class="btn btn-primary"><input type="radio" name="valide" value="4" required /> est la bonne reponse<br></label>
                </div>
            </div>

        </div>
        <div class="col-md-offset-1" style="padding-top: 5px; padding-bottom: 5px">
            <input type="reset" class="btn btn-default" value="effacer">&nbsp;
            <input type="submit" class="btn btn-default" value="creer question" name="creer">
        </div>
    </form>
</c:if>
<div style="padding-top: 20px;border-top-style: ridge"></div>
<div class="container"><a href="<c:url value="/ListeDesQcmServlet"/>" class="btn btn-default">Retour</a></div>
