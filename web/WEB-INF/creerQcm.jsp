<%-- 
    Document   : creerQcm
    Created on : 4 avr. 2017, 12:58:01
    Author     : admin
--%>
<c:if test="${requestscope.qcm}">
    <table>
        <c:forEach items="${requestScope.qcm.lesQuestions}" var="laQuestion">
            <tr><c:out value="${laQuestion.question}"/></tr>
            <c:forEach items="${laQuestion.lesChoix}" var="leChoix">
                <tr><c:out value="${leChoix.choix}"/><c:out value="${leChoix.estCorrect}"/></tr>
            </c:forEach>
        </c:forEach>   
    </table>
</c:if>

<form method="post" class="form-horizontal">
    <label for="nom">Entrez la question :</label><br />
    <textarea name="question" id="question" cols="100" class="form-control">tape ta question connard !</textarea>
    <div class="form-group">
        <label for="reponse1" class="col-sm-2 control-label">Réponse 1 :</label>
        <div class="col-sm-10">
        <input type="text" name="reponse1" id="reponse1" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 2 :</label>
        <div class="col-sm-10">
        <input type="text" name="reponse2" id="reponse" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 3 :</label>
        <div class="col-sm-10">
        <input type="text" name="reponse3" id="reponse" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 4 :</label>
        <div class="col-sm-10">
        <input type="text" name="reponse4" id="reponse" class="form-control"/>
        </div>
    </div>
    </table>
           
</form>