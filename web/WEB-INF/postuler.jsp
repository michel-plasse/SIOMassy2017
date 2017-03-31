
<div id="candidat-form">
<h1><c:out value="${requestScope.sessionFormation.nom}" /></h1>
<div id="infos-session">
        <c:out value="${requestScope.sessionFormation.descriptif}" />
</div>
<div id="content-form">
    <form method="POST" action="postuler">
        <p>
            <label style="vertical-align: top"for="file">Votre CV : </label>
            <input style="vertical-align: top" type="file" value="cv..." id="cv" name="cv" disabled/><br><br>
        </p>
        <p>
            <label style="vertical-align: top"for="motivations">Vos motivations : </label> 
            <textarea style="vertical-align: top" name="motivations" rows="4" cols="40" disabled>...</textarea><br>
        </p>


        <div style="text-align: center;">
            <br>
            <input type="hidden" name="idSessionFormation" value="${requestScope.sessionFormation.id_session}" />
            <input type="submit" value="Envoyer candidature"/> 
            <input type="reset" value="Effacer" />
        </div>
    </form>
</div>
<br>
<br>
<div id="backlink"> <a href="<c:url value="sessionsOuvertes" />">Annuler - Retour à la liste des sessions</a></div>
</div>
