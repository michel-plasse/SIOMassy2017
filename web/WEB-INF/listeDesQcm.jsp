<%-- 
    Document   : listeDesQcm
    Created on : 17 avr. 2017, 19:04:32
    Author     : thomas
--%>
<table class="table ">	
    <th>Module</th>
    <th>Nom du Qcm</th>
    <th class="text-center">Terminé</th>
    <th></th>
    <th></th>
        <c:forEach items="${requestScope.lesQcm}" var="unQcm">
        <tr>
            <td><c:out value="${unQcm.nomModule}" /></td>
            <td><c:out value="${unQcm.intitule}" /></td>
            <td class="text-center"><c:choose>
                    <c:when test="${unQcm.valide}">est terminé</c:when>
                    <c:when test="${!unQcm.valide}">
                        <form method="post">
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#confirmerValide">Valider</button>
                            <div class="modal fade" id="confirmerValide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="myModalLabel">Confirmer Validation ?</h4>
                                        </div>
                                        <div class="modal-body">
                                            Valider un Qcm le rendra disponible au passage et vous ne pourrez plus le modifier
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Retour</button>
                                            <button type="submit" class="btn btn-success" value="${unQcm.idQcm}" name="valider">valider</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
            </td>
            <td class="text-center"><a href="<c:url value="CreerQcm"><c:param name="idQcm" value="${unQcm.idQcm}"/></c:url>" class="btn btn-default" >
                    <c:choose>
                        <c:when test="${unQcm.valide}">Consulter</c:when>
                        <c:when test="${!unQcm.valide}">Editer</c:when>
                    </c:choose></a>
            </td>
            <td class="text-center"><form method="post"><c:choose>
                        <c:when test="${unQcm.valide}">
                            <c:choose>
                                <c:when test="${unQcm.archive}">Qcm archivé</c:when>
                                <c:when test="${!unQcm.archive}"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Archiver</button>
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">Confirmer Archivage ?</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Archiver ce Qcm le rendra indisponible au passage !
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Retour</button>
                                                    <button type="submit" class="btn btn-primary" value="${unQcm.idQcm}" name="archiver">Archiver</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>                       
                        </c:when>
                        <c:when test="${!unQcm.valide}"><button type="submit" class="btn btn-danger" value="${unQcm.idQcm}" name="supprimer">Supprimer</button></c:when>
                    </c:choose></form>
            </td>
        </tr>  
    </c:forEach>


    <form method="post" class="form-horizontal">
        <tr>
            <td><label for="nom">Module :</label>&nbsp;<select name="idModule">
                    <c:forEach items="${modules}" var="unModule">
                        <option value="${unModule.id}"> ${unModule.nom}</option>
                    </c:forEach>
                </select>
            </td>
            <td><label for="nom">Intitulé :</label>&nbsp;<input type="text" name="intitule"/></td>
            <td></td>
            <td></td>
            <td class="text-center"><button type="submit" class="btn btn-default" name="creer">Créer</button></td>
        </tr>
    </form>


</table>

