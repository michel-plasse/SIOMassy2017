<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulaire d'inscription</title>
</head>
<body>
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
				<td>Prénom : </td>
				<td><input type="text" name="prenom"><span> ${prenom}</span></td>
			</tr>

				<td><h3>Adresse :</h1></td>
			</tr>
			<tr>
				<td>Numéro : </td>
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
                                                <option value='AutreAmÃ©rique'>Autre pays (AmÃ©rique)</option>
                                                <option value='AutreOcÃ©anie'>Autre pays (OcÃ©anie)</option>
				</select></td>
			</tr>

			<tr>
				<td><button type="submit">S'inscrire</button></td>
			</tr>
		</form>
	</table>

	</div>
</body>
</html>