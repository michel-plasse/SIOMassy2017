<h1>Page de notes</h1>
<form method="POST" action="rentrerNotes">
<table class="table">	
    <th>Nom</th>
    <th>Prénom</th>
    <th>Note</th>
    <th>Commentaire</th>
  
    <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
        <tr>
            <td><c:out value="${unePersonne.nom}" /></td>
            <td><c:out value="${unePersonne.prenom}" /></td>
            <td><input type="text" name="<c:out value="${unePersonne.id}"  />" value=""  /></td>
            <td><input type="text" name = "<c:out value="${unePersonne.id}" />" value="" /></td>
        </tr>  
    </c:forEach>
   </table>
        
      <input type="submit" value="Envoyer" />
        
  </form>
