<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Espace personnel"/>
<c:if test='${user.nom eq "Leto"}'> <!-- if user.isFormateur ...
    <%@include file="espacePersoEtudiant.jsp" %>
</c:if>
<c:if test="${user.nom ne 'Leto'}">
    <p>${user.nom}, Vous n'�tes pas un chat, d�sol� ...</p>
</c:if>
</body>
</html>
