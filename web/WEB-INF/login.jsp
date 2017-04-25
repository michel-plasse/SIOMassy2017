
        <h1>Identifiez-vous</h1>
        <p><span>${inscriptionValide}</span></p>
        <form method="post" action="login">
            <table border =' 0'>
                <tr>
                    <td>Identifiant : </td>
                    <td>
                        <input type='text' name="login" required="required" class='form-control'/>
                        <span>${loginMsg}</span>
                    </td>    
                </tr>

                <tr>
                    <td>Mot de passe : </td>
                    <td>
                        <input type='password' name="password" required="required" class='form-control'/>
                        <span>${passwordMsg}</span>
                    </td>
                </tr>

                <tr>
                    <td><button type='submit' class='btn btn-success'>Se connecter</button></td>
                </tr>
                     
            </table>
        </form>
                    <br>
<a href="<c:url value="/inscrire"/>">Vous n'êtes pas encore inscrit ? Cliquez ici.</a>   