
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
                Evaluations non passées
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitulé évaluation</th>
                        </tr> 
                        <c:forEach items="${requestScope.lesEvals}" var="uneEval">
                            <tr>
                                <td><c:out value="${uneEval.dateDebutEval}" /></td>
                                <td><c:out value="${uneEval.intitule}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="<c:url value="/creerEvaluation"/>">Nouvelle évaluation</a>
            </div>
            <div>
                Evaluations non corrigées
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitulé évaluation</th>
                        </tr> 
                        <c:forEach items="${requestScope.lesEvals}" var="uneEval">
                            <tr>
                                <td><c:out value="${uneEval.dateDebutEval}" /></td>
                                <td><c:out value="${uneEval.intitule}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="<c:url value="/ListeDesQcmServlet"/>">Voir mes Qcm</a>
            </div>
            <div>
                Evaluations notées
                <div class="boiteParDefaut">
                    <table class="table">
                        <tr>
                            <th>Date de passage</th>
                            <th>Intitulé évaluation</th>
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
 
