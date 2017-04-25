<%-- 
    Document   : creerQcm
    Created on : 4 avr. 2017, 12:58:01
    Author     : admin
--%>
<div>${message}</div>
<h3>${qcm.intitule}</h3>
<c:if test="${not empty qcm.lesQuestions}">
    <div class="col-md-12">
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
                        <button type="submit" value="${laQuestion.idQuestion}" name="supprimerQuestion">Supprimer la question</button>
                    </c:if>
                </div>
            </c:forEach>
        </form>
    </div>
</c:if>
<c:if test="${!qcm.valide}">
    <form  method="post" class="form-horizontal">

        <label for="nom">Entrez la question :</label><br />
        <textarea name="question" id="question" cols="100" class="form-control" required></textarea><br>
        <div class="form-group">
            <label for="reponse1" class="col-sm-2 control-label">Réponse 1 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse1" id="reponse1" class="form-control"/>
                <div class="checkbox-inline">
                    <input type="radio" name="valide" value="1" required /> est la bonne reponse<br>
                </div>
            </div>

        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 2 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse2" id="reponse" class="form-control"/>
                <div class="checkbox-inline">
                    <input type="radio" name="valide" value="2" required /> est la bonne reponse<br>
                </div>
            </div>

        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 3 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse3" id="reponse" class="form-control"/>
                <div class="checkbox-inline">
                    <input type="radio" name="valide" value="3" required /> est la bonne reponse<br>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="nom" class="col-sm-2 control-label">Réponse 4 :</label>
            <div class="col-sm-10">
                <input type="text" name="reponse4" id="reponse" class="form-control"/>
                <div class="checkbox-inline">
                    <input type="radio" name="valide" value="4" required /> est la bonne reponse<br>
                </div>
            </div>
            <input type="reset" value="effacer">
            <input type="submit" value="creer question" name="creer">
        </div>

    </form>
</c:if>
<p><a href="<c:url value="/ListeDesQcmServlet"/>">retour</a></p>