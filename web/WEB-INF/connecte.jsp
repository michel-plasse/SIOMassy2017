<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header /> 
<div class="container">
<c:if test='${user.est_formateur eq false}'> <!-- if user.isFormateur ...
    <%@include file="espacePersoEtudiant.jsp" %>
</c:if>
<c:if test="${user.est_formateur eq true}">
    <p>${user.nom} ${user.nom}, Vous n'�tes pas un chat, d�sol� ...</p>
</c:if>-->
</div>
</body>
</html>
