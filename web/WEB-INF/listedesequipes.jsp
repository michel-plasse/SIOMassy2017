<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
          <div class="row">   
          <c:forEach items="${requestScope.equipes}" var="uneEquipe" >
            <div class="col-xs-6 col-lg-4">
                <div class="list-group">
                    <span class="list-group-item active">Equipe de <c:out value="${uneEquipe.createur.prenom}" /></span>
                    <c:forEach items="${uneEquipe.lesMembres}" var="membre">
                        <span class="list-group-item"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" /></span>
                    </c:forEach>
                </div>
            </div> 
          </c:forEach>
    </div> 
</div>