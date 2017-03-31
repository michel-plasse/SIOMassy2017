
        <h1>Identifiez-vous</h1>
        <p><span>${inscriptionValide}</span></p>
        <form method="post" action="login">
            <table border =' 0'>
                <tr>
                    <td>Identifiant : </td>
                    <td>
                        <input type='text' name="login" required="required"/>
                        <span>${loginMsg}</span>
                    </td>    
                </tr>

                <tr>
                    <td>Mot de passe : </td>
                    <td>
                        <input type='password' name="password" required="required"/>
                        <span>${passwordMsg}</span>
                    </td>
                </tr>

                <tr>
                    <td><button type='submit'>Se connecter</button></td>
                </tr>
            </table>
        </form>
