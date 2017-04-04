
<div id = "formateur-form">
    <div id="formateur-formBox">
        <img height="100" width="100" src="image/trombi/<c:out value="${user.photo}"/>"/>
    </div>
    <div>
        <div id="formateur-form">
            <div id="formateur-formBox" height="100">
                ${user.prenom} ${user.nom}<br>
                ${user.no_rue} ${user.rue}<br>
                ${user.code_postal} ${user.ville}<br>
                ${user.pays}<br>
                ${user.email}
            </div>
            <div>
                <a height="100" vertical-align="bottom" >Modifier profil</a>
            </div>
        </div>
        <div>
            
        </div>
    </div>
</div>
 