<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Mon espace personnel"/>
<p>${user.photo}${user.prenom} ${user.nom} </p>
</body>
</html>