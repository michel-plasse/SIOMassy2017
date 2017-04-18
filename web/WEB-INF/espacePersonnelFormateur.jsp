
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
            <div id="boiteParDefaut">
                Liste sessions
            </div>
            <div id="boiteParDefaut">
                Liste projets
            </div>
        </div>
        <div id="display-box2">
            <div>
                <div id="boiteParDefaut">
                    Liste évaluations non passées
                </div>
                <a href="<c:url value="/ListeDesQcmServlet"/>">créer Qcm</a>
            </div>
            <div id="boiteParDefaut">
                Liste évaluation non corrigées
            </div>
            <div id="boiteParDefaut">
                Liste évaluations notées
            </div>
        </div>    
    </div>
</div>
 