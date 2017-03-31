<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Espace personnel"/>
<c:if test='${user.nom eq "Leto"}'> <!-- if user.isFormateur ...
    <%@include file="espacePersoEtudiant.jsp" %>
</c:if>
<c:if test="${user.nom ne 'Leto'}">
    <p>${user.nom}, Vous n'êtes pas un chat, désolé ...</p>
</c:if>
</body>
</html>
