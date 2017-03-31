<%-- 
    Document   : trombinoscope
    Created on : 24 mars 2017, 14:25:33
    Author     : nate 
--%>

<div class="col-md-2">

    <ul class = "nav nav-pills nav-stacked">
        <li>
            <a href="#">Formation</a>
        </li>
        <c:forEach items="${requestScope.lesSessions}" var="uneSession">
            <li>
                <a href="#"><c:out value="${uneSession.nom}"/></a>
            </li>
        </c:forEach>
        <li>
            <a href="#">Formateurs</a>
        </li>
    </ul>
</div>


<div class="container-fluid col-md-10">
    <div class="row">
        <c:forEach items="${requestScope.lesPersonnes}" var="unePersonne">
            <div class="col-md-3" style="padding-bottom: 25px;">
                <b><span class="glyphicon glyphicon-user"></span>
                    <c:out value="${unePersonne.nom}" />
                    <c:out value="${unePersonne.prenom}" /></b><br>
                <img height="200" width="200" src="image/trombi/<c:out value="${unePersonne.photo}"/>"/><br>
                <i class="fa fa-phone" aria-hidden="true"></i> <c:out value="${unePersonne.no_tel}"/><br>
                <i class="fa fa-envelope" aria-hidden="true"></i> <c:out value="${unePersonne.email}"/>
            </div>
        </c:forEach>
    </div>
</div>

<div class="clearfix"></div>

