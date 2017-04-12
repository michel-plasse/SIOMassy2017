<h1>Page de notes</h1>
<form method="POST" action="rentrerNotes">

    <table class="table">	
        <th>Nom</th>
        <th>Prénom</th>
        <th>Note</th>
        <th>Commentaire</th>

        <c:forEach items="${lesPersonnes}" var="unePersonne">

            <tr>
                <td><c:out value="${unePersonne.nom}" /></td>
                <td><c:out value="${unePersonne.prenom}" /></td>
                <td>
                    <input type="number" step=".01" name="note"/>
                    <input type="hidden" name="id" value="${unePersonne.id}"/>
                </td>
                <td>
                    <input type="text" name="commentaire"/>
                </td>
            </tr>  

        </c:forEach>        
    </table>

    <input type="submit" value="Envoyer" />

</form>
