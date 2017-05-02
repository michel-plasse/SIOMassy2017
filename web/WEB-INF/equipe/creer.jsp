<div class="container-fluid">

    <div class="row-fluid">
        <h3><c:out value="${requestScope.title}" /></h3>
        <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
        <form method="POST">
            <!-- tableau des stagiaires sans équipe -->

            <div class="col-md-8">
                <fieldset>
                    <legend>Sélectionner les membres</legend>
                    <div class="sized-box">
                        <table class="table">
                            <c:forEach items="${requestScope.stagiairesSansEquipe}" var="stagiaire">
                                <c:if test="${sessionScope.user.id != stagiaire.id}">
                                <tr>
                                    <td><input type="checkbox" name="add" value="<c:out value="${stagiaire.id}" />" /></td>
                                    <td><c:out value="${stagiaire.nom}" /></td>
                                    <td><c:out value="${stagiaire.prenom}" /></td> 
                                </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </fieldset>
            </div>

            <div class="col-md-4">
                <fieldset>
                    <legend>Actions</legend>
                    <input type="hidden" name="idProjet" value="${requestScope.idProjet}" />
                    <button type="submit" class="btn btn-primary">Valider création</button><br />
                    <br />
                    <button type="reset" class="btn btn-info">Réinitialiser</button><br />
                    <br />
                    <a class="btn btn-default" href="<c:url value="/etudiant/equipe/index" ><c:param name="id" value="${requestScope.idProjet}" /></c:url>">
                        Annuler - Retour à la liste des équipes
                    </a>
                </fieldset>
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>