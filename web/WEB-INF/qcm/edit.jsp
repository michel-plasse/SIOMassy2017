<div class="col-md-10 col-md-offset-1">
    <c:if test="${!empty sessionScope.messageError}">
        <div class="alert alert-danger"><c:out value="${sessionScope.messageError}" /></div>
        <c:remove scope="session" var="messageError" />
    </c:if>
    <h3>Ajouter des questions :</h3>
    <br />
    <c:if test="${fn:length(requestScope.qcm.lesQuestions) gt 0}">
        <c:set scope="page" var="compteur" value="0" />
        <div class="well" style="max-height: 300px;overflow-y: scroll;">
            <form method="POST" id="deleteQ" action="<c:url value="/qcm/edit"><c:param name="id" value="${requestScope.qcm.idQcm}" /></c:url>">
                    <div class="panel-group" id="Q">
                    <c:forEach items="${requestScope.qcm.lesQuestions}" var="q">
                        <c:set var="compteur" value="${compteur + 1}" />
                        <div class="panel panel-default">
                            <div class="panel-heading" style="padding: 5px !important;">
                                <div class="col-md-11">
                                    <a data-toggle="collapse" style="text-decoration: none;" data-parent="#Q" href="#<c:out value="${compteur}" />">
                                        <h4 class="panel-title" style="font-size: 18px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
                                            #<c:out value="${compteur}" /> - <c:out value="${q.question}" />
                                        </h4>
                                    </a>
                                </div>
                                <div class="col-md-1 text-right">
                                    <button type="submit" name="delete" value="<c:out value="${q.idQuestion}" />">
                                        <i class="fa fa-times text-danger" aria-hidden="true"></i>
                                    </button>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div id="<c:out value="${compteur}" />" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <c:forEach items="${q.lesChoix}" var="c">
                                        <b <c:if test="${c.value.estCorrect}">class="green"</c:if> ><c:out value="R -  ${c.value.choix}" /></b><br/>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </form>
        </div>
    </c:if>
    <form class="form-horizontal" method="POST" id="ajoutQ" action="<c:url value="/qcm/edit"><c:param name="id" value="${requestScope.qcm.idQcm}" /></c:url>">
        <div class="form-group">
            <label class="control-label col-sm-2" for="question">Question :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="question"  name="question" required />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="reponse">Réponses :</label>
            <div id="liste-question" class="col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control" id="reponse" name="reponse[1]" required />
                    <span class="input-group-addon"><input type="checkbox" name="correcte[1]"></span>
                </div>
                <div class="input-group" style="margin-top: 15px;">
                    <input type="text" class="form-control" id="reponse" name="reponse[2]" required />
                    <span class="input-group-addon"><input type="checkbox" name="correcte[2]"></span>
                </div>
            </div>
            <div class="col-sm-2">
                <button type="button" id="addRep" class="btn btn-success" title="ajouter une réponse"><i class="fa fa-plus fa-2x" aria-hidden="true"></i></button>
                <button type="button" id="delRep" class="btn btn-danger" title="retirer une réponse"><i class="fa fa-minus fa-2x" aria-hidden="true"></i></button>
                <br />
                <small><i>Max : 6 réponses</i></small>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="submit" class="btn btn-primary" name="sendQuestion" value="1">Valider question</button>
                <button type="reset" class="btn btn-info">Réinitialiser</button>
            </div>
        </div>
    </form>
    <br />
    <hr style="width: 100%; color: black; height: 1px; background-color:black;" />
    <h3>Paramètres du QCM :</h3>
    <br />
    <form class="form-horizontal" id="modifinfo" method="POST" action="<c:url value="/qcm/edit"><c:param name="id" value="${requestScope.qcm.idQcm}" /></c:url>">
        <div class="form-group">
            <label class="control-label col-sm-2" for="titre">Titre :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="titre" name="titre" value="<c:out value="${requestScope.qcm.intitule}" />" required />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="theme">Module :</label>
            <div class="col-sm-10">
                <select class="form-control" name="module" id="module" required>
                    <option value="" selected disabled>#selectionner un thème</option>
                    <c:forEach items="${requestScope.modules}" var="m">
                        <option value="${m.id}" <c:if test="${requestScope.qcm.idModule == m.id}">selected</c:if>><c:out value="- ${m.nom}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="active">Etat :</label>
            <div class="col-sm-10">
                <select class="form-control" name="active" id="active">
                    <option value="false" <c:if test="${!requestScope.qcm.valide || empty requestScope.qcm.valide}">selected</c:if>>Inactif</option>
                    <option value="true" <c:if test="${requestScope.qcm.valide}">selected</c:if>>Actif</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" form="modifinfo" name="modif" class="btn btn-primary" value="1">Valider</button>
                <button type="submit" form="modifinfo" name="erase" class="btn btn-danger" value="1">Supprimer le Qcm</button>
            </div>
        </div>
    </form>
</div>

<div class="clearfix"></div>