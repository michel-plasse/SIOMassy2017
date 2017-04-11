<%-- 
    Document   : emailTrombinoscope
    Created on : 11 avr. 2017, 09:52:14
    Author     : nate
--%>

<body>
    <form method="post" class="form-horizontal">
        
        <div class="form-group">
            <label class="col-sm-2 control-label">Expéditeur</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="<c:out value="${user.email}"/>"disabled>
            </div>
        </div>
            
        <div class="form-group">
            <label class="col-sm-2 control-label">Destinataires</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" value="<c:forEach items="${paramValues.email}" var="email"><c:out value="${email}"/>;</c:forEach>" />
            </div>
        </div>
            
        <div class="form-group">
            <label class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10"><textarea rows="12" name="email" class="form-control" placeholder="Votre email."></textarea>
            </div>
        </div>
            
        <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10"><input type="submit" value="Envoyer" class="btn btn-success">
            </div>
        </div>

    </form>
</body>
</html>
