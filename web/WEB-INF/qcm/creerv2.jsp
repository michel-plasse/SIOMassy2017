<div class="col-md-10 col-md-offset-1">
    <c:if test="${!empty sessionScope.messageError}">
        <div class="alert alert-danger"><c:out value="${sessionScope.messageError}" /></div>
        <c:remove scope="session" var="messageError" />
    </c:if>
    <h3><c:out value="${requestScope.title}" /></h3>
    <br /> 
    <form class="form-horizontal" method="POST" action="<c:url value="/qcm/creer" />">
        <div class="form-group">
            <label class="control-label col-sm-2" for="titre">Titre QCM :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="titre"  name="titre"  required />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="theme">Module :</label>
            <div class="col-sm-10">
                <select class="form-control" name="module" id="module" required>
                    <option value="" selected disabled>#selectionner un thème</option>
                    <c:forEach items="${requestScope.modules}" var="m">
                        <option value="${m.id}"><c:out value="- ${m.nom}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary" name="submitqcm" value="1">Valider</button>
                <a href="<c:url value="/" />"><button class="btn btn-info">Annuler</button></a>
            </div>
        </div>
    </form>
</div>

<div class="clearfix"></div>