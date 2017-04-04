<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <div
    <div class="col-md-9">
    <div class="row-fluid">
        <h3><c:out value="${requestScope.title}" /></h3><br />
          <c:forEach items="${requestScope.equipes}" var="uneEquipe" >
            <div class="col-md-4">
                <div class="list-group">
                    <span class="list-group-item active">Equipe de <c:out value="${uneEquipe.createur.prenom}" /></span>
                    <c:forEach items="${uneEquipe.lesMembres}" var="membre">
                        <span class="list-group-item"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" /></span>
                    </c:forEach>
                </div>
            </div> 
          </c:forEach>
    </div>
    
          
    <div class="clearfix section-grp"></div>   
    
    <div class="row-fluid">
        <h3><c:out value="Liste des membres sans équipe" /></h3><br />    
    </div>
    </div>
</div>