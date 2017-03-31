
<p>Merci de vous être enregistré <span>${leNom } ${lePrenom }</span>.
Veuillez cliquez sur le lien afin de finaliser votre inscription.
<!--<a href="http://localhost/SIOMassy2017/confirmerinscription?token=${leToken}">Confirmer</a>-->
<a href="<c:url value="ConfirmerInscription"><c:param name="token" value="${requestScope.leToken}"/></c:url>">Confirmer</a> <!--(avec jstl)-->
