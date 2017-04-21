<%-- 
    Document   : creerQcm
    Created on : 4 avr. 2017, 12:58:01
    Author     : admin
--%>
<h3><c:out value="${requestScope.qcm.intitule}"/></h3>
<c:if test="${not empty requestScope.qcm.lesQuestions}">
    <div class="col-md-12">
        <c:forEach items="${requestScope.qcm.lesQuestions}" var="laQuestion">
            <div class="col-md-8 col-md-offset-2"><div class="bg-info"><h4><c:out value="${laQuestion.question}"/></h4></div>
            <c:forEach items="${laQuestion.lesChoix}" var="leChoix">
                <div class="col-md-3"></div><div class="col-md-7"><c:out value="${leChoix.value.choix}"/>&nbsp;<c:out value="${leChoix.value.estCorrect}"/></div><div class="col-md-2"></div>
            </c:forEach>
            </div>
        </c:forEach>   
    </div>
</c:if>

<form  method="post" class="form-horizontal">

    <label for="nom">Entrez la question :</label><br />
    <textarea name="question" id="question" cols="100" class="form-control"></textarea><br>
    <div class="form-group">
        <label for="reponse1" class="col-sm-2 control-label">Réponse 1 :</label>
        <div class="col-sm-10">
            <input type="text" name="reponse1" id="reponse1" class="form-control"/>
            <div class="checkbox-inline">
                <input type="radio" name="valide" value="true" > est la bonne reponse<br>
            </div>
        </div>

    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 2 :</label>
        <div class="col-sm-10">
            <input type="text" name="reponse2" id="reponse" class="form-control"/>
            <div class="checkbox-inline">
                <input type="radio" name="valide" value="true" > est la bonne reponse<br>
            </div>
        </div>

    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 3 :</label>
        <div class="col-sm-10">
            <input type="text" name="reponse3" id="reponse" class="form-control"/>
            <div class="checkbox-inline">
                <input type="radio" name="valide" value="true" > est la bonne reponse<br>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="nom" class="col-sm-2 control-label">Réponse 4 :</label>
        <div class="col-sm-10">
            <input type="text" name="reponse4" id="reponse" class="form-control"/>
            <div class="checkbox-inline">
                <input type="radio" name="valide" value="true" > est la bonne reponse<br>
            </div>
        </div>
        <input type="reset" value="Reset">
        <input type="submit" value="Submit">
    </div>
</table>


</form>