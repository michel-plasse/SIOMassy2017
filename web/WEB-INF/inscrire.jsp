  <p><span>${messageErreurValidation}</span></p>
  <p><span style='color:#d9534f'>${message}</span></p>
	<table border=0>
		<form method="post">
			<tr>
				<td>E-mail : </td>
				<td><input type="text" name="email" class='form-control' value='<c:out value='${personne.email}'/>'><span style='color:#d9534f'> ${email}</span></td>
			</tr>
			<tr>
				<td>Confirmation de l'e-mail : </td>
				<td><input type="text" name="email2" class='form-control'><span style='color:#d9534f'> ${email2}</span></td>
			</tr>

			<tr>
				<td>Mot de passe : </td>
				<td><input type="password" name="password" class='form-control'><span style='color:#d9534f'> ${password}</span></td>
			</tr>
			<tr>
				<td>Confirmation du mot de passe : </td>
				<td><input type="password" name="password2" class='form-control'><span style='color:#d9534f'> ${password2}</span></td>
			</tr>

			<tr>
				<td>Nom : </td>
                                <td><input type="text" name="nom" class='form-control' value='<c:out value='${personne.nom}'/>'><span style='color:#d9534f'> ${nom}</span></td>
			</tr>
			<tr>
				<td>Prénom : </td>
				<td><input type="text" name="prenom" class='form-control' value='<c:out value='${personne.prenom}'/>'><span style='color:#d9534f'> ${prenom}</span></td>
			</tr>
                        
                        <tr>
				<td>Téléphone : </td>
				<td><input type="text" name="no_phone" class='form-control' value='<c:out value='${personne.no_tel}'/>'><span style='color:#d9534f'> ${no_phone}</span></td>
			</tr>

				<td><h3>Adresse :</h1></td>
			</tr>
			<tr>
				<td>Numéro : </td>
				<td><input type="text" name="no_rue" class='form-control' value='<c:out value='${personne.no_rue}'/>'></td>
			</tr>
			<tr>
				<td>Rue : </td>
				<td><input type="text" name="nom_rue" class='form-control' value='<c:out value='${personne.rue}'/>'></td>
			</tr>
			<tr>
				<td>Code Postal : </td>
				<td><input type="text" name="code_postal" class='form-control' value='<c:out value='${personne.code_postal}'/>'></td>
			</tr>
			<tr>
				<td>Ville : </td>
				<td><input type="text" name="ville" class='form-control' value='<c:out value='${personne.ville}'/>'></td>
			</tr>
			<tr>
				<td>Pays : </td>
				<td><select name="pays" class='form-control'>
						<option value="France" selected="selected">France</option>
                                                <option value='OutreMer'>Outre-Mer</option>
						<option value="AutreEurope">Autre pays (Europe)</option>
						<option value="AutreAfrique">Autre pays (Afrique)</option>
                                                <option value='AutreAsie'>Autre pays (Asie)</option>
                                                <option value='AutreAmérique'>Autre pays (Amérique)</option>
                                                <option value='AutreOcéanie'>Autre pays (Océanie)</option>
				</select></td>
			</tr>

			<tr>
				<td><button type="submit" class='btn btn-success'>S'inscrire</button></td>
			</tr>
		</form>
	</table>
