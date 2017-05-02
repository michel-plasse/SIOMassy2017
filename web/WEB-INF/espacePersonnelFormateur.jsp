<c:if test="${!empty sessionScope.messageOk}">
    <div class="alert alert-success feedback"><c:out value="${sessionScope.messageOk}" /></div>
    <c:remove scope="session" var="messageOk" />
</c:if>
<c:if test="${!empty sessionScope.messageError}">
    <div class="alert alert-danger feedback"><c:out value="${sessionScope.messageError}" /></div>
    <c:remove scope="session" var="messageError" />
</c:if>
<div id="formateur-form">
    <div id="formateur-formBox">
        <img height="100" width="100" src="image/trombi/<c:out value="${user.photo}"/>"/>
    </div>
    <div>
        <div id="formateur-form">
            <div id="formateur-formBox">
                ${user.prenom} ${user.nom}<br>
                ${user.no_rue} ${user.rue}<br>
                ${user.code_postal} ${user.ville}<br>
                ${user.pays}<br>
                ${user.email}
            </div>
            <div style="display:table;height: 100px;">
                <div style="display:table-cell;vertical-align:bottom"><a>Modifier profil</a></div>
            </div>
        </div>
        <div id="display-box1">
            <div>
                Sessions
                <div class="boiteParDefaut">
                    Liste sessions
                </div>
            </div>
            <div>
                Projets
                <div class="boiteParDefaut">
                    Liste projets
                </div>
            </div>
        </div>
        <div id="display-box2">
            <div>
                Evaluations non pass�es
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitul� �valuation</th>
                        </tr> 
                        <c:forEach items="${requestScope.lesEvals}" var="uneEval">
                            <tr>
                                <td><c:out value="${uneEval.dateDebutEval}" /></td>
                                <td><c:out value="${uneEval.intitule}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="<c:url value="/creerEvaluation"/>">Nouvelle �valuation</a>
            </div>
            <div>
                Evaluations non corrig�es
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitul� �valuation</th>
                        </tr> 
                        <c:forEach items="${requestScope.lesEvals}" var="uneEval">
                            <tr>
                                <td><c:out value="${uneEval.dateDebutEval}" /></td>
                                <td>
                                    <a href="<c:url value="rentrerNotes"><c:param name="id" value="${uneEval.idEvaluation}"/>
                                       </c:url>" title="Notez les �l�ves"><c:out value="${uneEval.intitule}" /></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="<c:url value="/ListeDesQcmServlet"/>">Voir mes Qcm</a>
            </div>
            <div>
                Evaluations not�es
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitul� �valuation</th>
                            <th>Note moyenne</th>
                        </tr> 
                        <c:forEach items="${requestScope.lesEvals}" var="uneEval">
                            <tr>
                                <td><c:out value="${uneEval.dateDebutEval}" /></td>
                                <td><c:out value="${uneEval.intitule}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>    
    </div>
</div>

