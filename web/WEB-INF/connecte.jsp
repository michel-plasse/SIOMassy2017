<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<p:header titre="Espace personnel"/>
        <p>Bonjour ${sessionScope["user"].nom}</p>
    </body>
</html>
