  <p><span>${messageErreurValidation}</span></p>
	<table border=0>
		<form method="post">
			<tr>
				<td>E-mail : </td>
				<td><input type="text" name="email"><span> ${email}</span></td>
			</tr>
			<tr>
				<td>Confirmation de l'e-mail : </td>
				<td><input type="text" name="email2"><span> ${email2}</span></td>
			</tr>

			<tr>
				<td>Mot de passe : </td>
				<td><input type="password" name="password"><span> ${password}</span></td>
			</tr>
			<tr>
				<td>Confirmation du mot de passe : </td>
				<td><input type="password" name="password2"><span> ${password2}</span></td>
			</tr>

			<tr>
				<td>Nom : </td>
				<td><input type="text" name="nom"><span> ${nom}</span></td>
			</tr>
			<tr>
				<td>Pr�nom : </td>
				<td><input type="text" name="prenom"><span> ${prenom}</span></td>
			</tr>
                        
                        <tr>
				<td>T�l�phone : </td>
				<td><input type="text" name="no_phone"></td>
			</tr>

				<td><h3>Adresse :</h1></td>
			</tr>
			<tr>
				<td>Num�ro : </td>
				<td><input type="text" name="no_rue"></td>
			</tr>
			<tr>
				<td>Rue : </td>
				<td><input type="text" name="nom_rue"></td>
			</tr>
			<tr>
				<td>Code Postal : </td>
				<td><input type="text" name="code_postal"></td>
			</tr>
			<tr>
				<td>Ville : </td>
				<td><input type="text" name="ville"></td>
			</tr>
			<tr>
				<td>Pays : </td>
				<td><select name="pays">
						<option value="France" selected="selected">France</option>
                                                <option value='OutreMer'>Outre-Mer</option>
						<option value="AutreEurope">Autre pays (Europe)</option>
						<option value="AutreAfrique">Autre pays (Afrique)</option>
                                                <option value='AutreAsie'>Autre pays (Asie)</option>
                                                <option value='AutreAm�rique'>Autre pays (Am�rique)</option>
                                                <option value='AutreOc�anie'>Autre pays (Oc�anie)</option>
				</select></td>
			</tr>

			<tr>
				<td><button type="submit">S'inscrire</button></td>
			</tr>
		</form>
	</table>
