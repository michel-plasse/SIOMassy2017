<h1>Notation de l'évaluation : <c:out value="${requestScope.lesNotes[1].evaluation.intitule}" /></h1>
<form method="POST" action="rentrerNotes">

    <table class="table">	
        <th>Nom</th>
        <th>Prénom</th>
        <th>Note</th>
        <th>Commentaire</th>

        <c:forEach items="${lesNotes}" var="n">

            <tr>
                <td><c:out value="${n.etudiant.nom}" /></td>
                <td><c:out value="${n.etudiant.prenom}" /></td>
                <td>
                    <input type="number" step="0.5" name="note" value="${n.note}"/>
                    <input type="hidden" name="id" value="${n.id_note}"/>
                </td>
                <td>
                    <input type="text" name="commentaire" value="${n.commentaire}"/>
                </td>
            </tr>  

        </c:forEach>        
    </table>

    <input type="submit" value="Envoyer" />

</form>
