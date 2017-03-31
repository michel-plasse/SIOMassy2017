
<c:if test='${user.est_formateur eq false}'> <!-- if user.isFormateur ...
    <%@include file="espacePersoEtudiant.jsp" %>
</c:if>
<c:if test="${user.est_formateur eq true}">
    <%@include file="espacePersonnelFormateur.jsp" %>
</c:if>-->

