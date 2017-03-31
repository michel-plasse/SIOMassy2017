       <h1>La liste de candidatures</h1>
        <form action="${pageContext.request.contextPath}/candidatures">
            <table>
                <tr>
                    <td>
                         <select name="statut">
                              <option value="" selected="">choisissez un statut</option>
                            <c:forEach var="unEtat" items="${lesEtats}">                
                                <option value="${unEtat["libelle"]}" <c:if test='${ param.statut == unEtat["libelle"]}' >selected</c:if>>${unEtat["libelle"]}</option>                           
                            </c:forEach>
                         </select>
                    </td>
                    <td>
                         <select name="formationNom">
                              <option value="" selected="">choisissez une session</option>
                             <c:forEach var="unFormation" items="${lesFormations}">
                                <option value="${unFormation["nom"]}" <c:if test='${ param.formationNom == unFormation["nom"]}' >selected</c:if>>${unFormation["nom"]}</option>                           
                             </c:forEach>                              
                         </select>
                    </td>
                    <td><input type="submit" value="Rechercher"></td>
                </tr>
            </table>            
        </form>
           
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Statut</th>
                <th>Date_debut</th>
                <th>Date_fin</th>
                <th>Session</th>
                <th>Date_de_postulation</th>
            </tr>
        <c:forEach var="unCandidature" items="${lesCandidatures}">
            <tr>
                <td>${unCandidature["nom"]}</td>
                <td>${unCandidature["prenom"]}</td>
                <td>${unCandidature["statut"]}</td>
                <td>${unCandidature["debut"]}</td>
                <td>${unCandidature["fin"]}</td>
                <td>${unCandidature["formationNom"]}</td>
                <td>${unCandidature["effectue"]}</td>
            </tr>
            
        </c:forEach>
        </table>
