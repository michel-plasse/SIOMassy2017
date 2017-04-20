<%-- 
    Document   : listeDesQcm
    Created on : 17 avr. 2017, 19:04:32
    Author     : thomas
--%>
<table class="table">	
    <th>Module</th>
    <th>Nom du Qcm</th>
    <th>Terminé</th>
    <th></th>

    <c:forEach items="${requestScope.lesQcm}" var="unQcm">
        <tr>
            <td><c:out value="${unQcm.nomModule}" /></td>
            <td><c:out value="${unQcm.intitule}" /></td>
            <td><c:choose>
                    <c:when test="${unQcm.valide}">est terminé</c:when>
                    <c:when test="${!unQcm.valide}">pas terminé</c:when>
            </c:choose></td>
            <td><a href="<c:url value="CreerQcm">
                       <c:param name="idQcm" value="${unQcm.idQcm}"/>
                   </c:url>">Editer</a></td>
            <td><button type="">Supprimer</button></td>
        </tr>  
    </c:forEach>
        <form method="post" class="form-horizontal">
        <tr>
            <td><label for="nom">Module :</label>&nbsp;<select name="idModule">
                    <c:forEach items="${modules}" var="unModule">
                        <option value="${unModule.id}"> ${unModule.nom}</option>
                    </c:forEach>
                </select>
            </td>
            <td><label for="nom">Intitulé :</label>&nbsp;<input type="text" name="intitule"/></td>
            <td></td>
            <td><button type="submit">Créer</button></td>
        </tr>
        </form>


</table>