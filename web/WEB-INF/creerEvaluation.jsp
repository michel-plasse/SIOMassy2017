<h1>Creer une Evaluation</h1>

<form method="post">
    <label for="nom">date :</label>&nbsp;<input type="datetime-local" name="date"/>
    <label for="nom">Module :</label>&nbsp;<select name="idModule">
        <c:forEach items="${modules}" var="unModule">
            <option value="${unModule.id}"> ${unModule.nom}</option>
        </c:forEach>
    </select>
    <label for="nom">Session :</label>&nbsp;<select name="idSession">
        <c:forEach items="${sessions}" var="uneSession">
            <option value="${uneSession.id_session}"> ${uneSession.nom} ${uneSession.id_session} </option>
        </c:forEach>
    </select>
    <label for="nom">Intitulé</label>&nbsp;<input type="text" name="intitule"/>
    <button type="submit">Créer</button>
</form>