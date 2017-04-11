<h1>Thomas</h1>

<form method="post">
    Date : ...
    Module : <select name="idModule">
        <c:forEach items="${modules}" var="unModule">
            <option value="${unModule.id}"> ${unModule.nom}</option>
        </c:forEach>
    </select>
    <button type="submit">Créer</button>
</form>