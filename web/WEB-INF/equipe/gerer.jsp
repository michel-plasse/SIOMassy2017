<div class="col-md-12">

    <h3><i class="fa fa-users" aria-hidden="true"></i> <c:out value="${requestScope.title}" /></h3>
    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />


    <form name="manage" method="POST" action="<c:url value="equipe/gerer"><c:param name="id" value="${equipeGeree.id}" /></c:url>">
            <!-- tableau des membres présents dans l'équipe -->
            <div class="col-md-4">
                <fieldset>
                    <legend>Retirer des membres</legend>
                    <div class="sized-box">
                        <table class="table">
                        <c:forEach items="${requestScope.equipeGeree.lesMembres}" var="unMembre">
                            <tr>
                                <td><input type="checkbox" name="delete" value="<c:out value="${unMembre.value.id}" />" /></td>
                                <td><c:out value="${unMembre.value.nom}" /></td>
                                <td><c:out value="${unMembre.value.prenom}" /></td>  
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </fieldset>
        </div>

        <!-- tableau des stagiaires sans équipe -->

        <div class="col-md-4">
            <fieldset>
                <legend>Ajouter des membres</legend>
                <div class="sized-box">
                    <table class="table">
                        <c:forEach items="${requestScope.stagiairesSansEquipe}" var="stagiaire">
                            <tr>
                                <td><input type="checkbox" name="add" value="<c:out value="${stagiaire.id}" />" /></td>
                                <td><c:out value="${stagiaire.nom}" /></td>
                                <td><c:out value="${stagiaire.prenom}" /></td> 
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
            </fieldset>
        </div>

        <div class="col-md-4">
            <fieldset>
                <legend>Actions</legend>
                <input type="hidden" name="idProjet" value="${requestScope.equipeGeree.idProjet}" />
                <button type="submit" name="modifier" class="btn btn-primary" value="1">Valider modification(s)</button><br />
                <br />
                <button type="reset" class="btn btn-info">Réinitialiser</button><br />
                <br />
                <button name="supprimer" class="btn btn-danger" value="1">Supprimer l'équipe</button><br />
                <br />
                <a class="btn btn-default" href="<c:url value="equipe/index" ><c:param name="id" value="${requestScope.equipeGeree.idProjet}" /></c:url>">
                    Retour à la liste des équipes
                </a>
            </fieldset>
        </div>
    </form>
    <div class="clearfix"></div>
    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
    <c:if test="${!empty sessionScope.messageOk}">
        <div class="alert alert-success feedback"><c:out value="${sessionScope.messageOk}" /></div>
        <c:remove scope="session" var="messageOk" />
    </c:if>
    <c:if test="${!empty sessionScope.messageError}">
        <div class="alert alert-danger feedback"><c:out value="${sessionScope.messageError}" /></div>
        <c:remove scope="session" var="messageError" />
    </c:if>
</div>
<div class="clearfix"></div>