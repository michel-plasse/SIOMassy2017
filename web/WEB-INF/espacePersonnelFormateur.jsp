
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
                <div id="boiteParDefaut">
                    Liste sessions
                </div>
            </div>
            <div>
                Projets
                <div id="boiteParDefaut">
                    Liste projets
                </div>
            </div>
        </div>
        <div id="display-box2">
            <div>
                Evaluations non passées
                <div id="boiteParDefaut">
                     <table class="table">	
                        <c:forEach items="${requestScope.lesQcm}" var="unQcm">
                            <tr>
                                <td><c:out value="${unQcm.intitule}" /></td>
                                
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="<c:url value="/ListeDesQcmServlet"/>">Voir mes Qcm</a>
            </div>
            <div>
                Evaluations non corrigées
                <div id="boiteParDefaut">
                    Liste évaluation non corrigées
                </div>
            </div>
            <div>
                Evaluations notées
                <div id="boiteParDefaut">
                    Liste évaluations notées
                </div>
            </div>
        </div>    
    </div>
</div>
 