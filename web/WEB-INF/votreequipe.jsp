<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-4">
            <h3><c:out value="${requestScope.title}" /></h3><br />
            <div class="list-group">
                <span class="list-group-item active">Votre Equipe</span>
                <c:forEach items="${requestScope.equipe.lesMembres}" var="membre">
                    <span class="list-group-item"><c:out value="${membre.value.prenom}" /> <c:out value="${membre.value.nom}" /></span>
                </c:forEach>
            </div>
        </div>
    </div>           
</div>