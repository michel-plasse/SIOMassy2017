<div class="container-fluid">

    <div class="row-fluid">
        
        <form name="manage" method="POST" action="<c:url value="/equipe/gerer" />">
            <div class="col-md-12">
                <!-- tableau des membres pr�sents dans l'�quipe -->
                <div class="col-md-4 sized-box">
                    <fieldset>
                        <legend>Retirer</legend>
                        <table class="table">
                            <c:forEach items="${requestScope.equipeGeree.lesMembres}" var="unMembre">
                                <tr>
                                    <td><input type="checkbox" name="delete[]" value="<c:out value="${unMembre.value.id}" />" /></td>
                                    <td><c:out value="${unMembre.value.nom}" /></td>
                                    <td><c:out value="${unMembre.value.prenom}" /></td>  
                                </tr>
                            </c:forEach>
                        </table> 
                    </fieldset>
                </div>

                <!-- tableau des stagiaires sans �quipe -->

                <div class="col-md-4 sized-box">
                    <fieldset>
                        <legend>Ajouter</legend>
                        <table class="table">
                            <c:forEach items="${stagiairesSansEquipe}" var="stagiaire">
                                <tr>
                                    <td><input type="checkbox" name="delete[]" value="<c:out value="${stagiaire.id}" />" /></td>
                                    <td><c:out value="${stagiaire.nom}" /></td>
                                    <td><c:out value="${stagiaire.prenom}" /></td> 
                                </tr>
                            </c:forEach>
                        </table> 
                    </fieldset>
                </div>
                
                <div class="col-md-4">
                    <fieldset>
                        <legend>Actions</legend>
                        <button type="submit" name="modifier" class="btn btn-primary">Valider modification(s)</button><br />
                        <br />
                        <button type="reset" class="btn btn-info">R�initialiser</button><br />
                        <br />
                        <button name="supprimer" class="btn btn-danger">Supprimer l'�quipe</button>  
                    </fieldset>
                </div>
                <div class="clearfix"></div>
           </div>
        </form>
    </div>
                   
                  
</div>