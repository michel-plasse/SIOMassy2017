<%-- 
    Document   : trombinoscope
    Created on : 24 mars 2017, 14:25:33
    Author     : nate 
--%>
<script type="text/javascript" src="<c:url value="/inc/bootstrap/js/offcanvas.js"/>"></script>
<div class="container" style="padding-left: 0px">
    <div class="col-xs-4 col-sm-2 sidebar-offcanvas" id="sidebar">
        <div class="list-group">

            <a class="list-group-item active">Formation</a>
            <c:forEach items="${requestScope.lesSessions}" var="uneSession">
                <a href="<c:url value="trombinoscope">
                       <c:param name="idSession" value="${uneSession.id_session}"/>
                   </c:url>" class="list-group-item"><c:out value="${uneSession.nom}"/></a>
            </c:forEach>
        </div>

    </div>
    <div class="row row-offcanvas row-offcanvas-left">

        <div class="col-xs-12 col-sm-9" >

            <form method="post">
                <div class="row">
                    <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
                        <div class="col-xs-6 col-lg-4" style="padding-bottom: 25px">
                            <b><span class="glyphicon glyphicon-user"></span>
                                <c:out value="${unePersonne.nom}" />
                                <c:out value="${unePersonne.prenom}" /></b>
                                <p>
                                    <c:if test="${empty unePersonne.photo}"> 
                                        <img height="200" width="200" src="image/trombi/portrait.jpg"/></p>
                                    </c:if>
                                    <c:if test="${not empty unePersonne.photo}">
                                        <img height="200" width="200" src="image/trombi/<c:out value="${unePersonne.photo}"/>"/></p>
                                    </c:if>
                            <p><i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${unePersonne.no_tel}"/></p>
                            <p><i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${unePersonne.email}"/></p>
                            <p><input type="checkbox" name="email" value="<c:out value="${unePersonne.email}"/>">&nbsp; Ajouter à l'email</p>
                        </div>
                    </c:forEach>
                </div>
                <p><input type="submit" value='Envoyer un email aux personnes sélectionnées'></p>
            </form>
        </div>



    </div>
</div>
