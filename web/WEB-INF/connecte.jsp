
<c:if test='${user.est_formateur eq false}'>
    <%@include file="espacePersoEtudiant.jsp" %>
</c:if>
<c:if test="${user.est_formateur eq true}">
    <%@include file="espacePersonnelFormateur.jsp" %>
</c:if>

