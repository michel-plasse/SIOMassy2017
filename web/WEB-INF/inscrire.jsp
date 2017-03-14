<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulaire d'inscription</title>
</head>
<body>
	<table border=0>
		<form method="post">
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email"><span> ${email}</span></td>
			</tr>
			<tr>
				<td>Confirmation de l'e-mail</td>
				<td><input type="text" name="email2"><span> ${email2}</span></td>
			</tr>

			<tr>
				<td>Mot de passe</td>
				<td><input type="text" name="password"><span> ${password}</span></td>
			</tr>
			<tr>
				<td>Confirmation du mot de passe</td>
				<td><input type="text" name="password2"><span> ${password2}</span></td>
			</tr>

			<tr>
				<td>Nom</td>
				<td><input type="text" name="nom"><span> ${nom}</span></td>
			</tr>
			<tr>
				<td>Pr�nom</td>
				<td><input type="text" name="prenom"><span> ${prenom}</span></td>
			</tr>
			<!-- 		Num�ro de t�l�phone <input type="text" name="no_telephone"><br><br> -->

				<td><h3>Adresse :</h1></td>
			</tr>
			<tr>
				<td>Num�ro</td>
				<td><input type="text" name="no_rue"></td>
			</tr>
			<tr>
				<td>Rue</td>
				<td><input type="text" name="nom_rue"></td>
			</tr>
			<tr>
				<td>Code Postal</td>
				<td><input type="text" name="code_postal"></td>
			</tr>
			<tr>
				<td>Ville</td>
				<td><input type="text" name="ville"></td>
			</tr>
			<tr>
				<td>Pays</td>
				<td><select name="pays">
						<option value="France" selected="selected">France</option>
						<option value="Afghanistan">Afghanistan</option>
						<option value="Afrique Centrale">Afrique Centrale</option>
						<option value="Afrique du sud">Afrique du Sud</option>
						<option value="Albanie">Albanie</option>
						<option value="Alg�rie">Alg�rie</option>
						<option value="Allemagne">Allemagne</option>
						<option value="Andorre">Andorre</option>
						<option value="Angola">Angola</option>
						<option value="Anguilla">Anguilla</option>
						<option value="Arabie Saoudite">Arabie Saoudite</option>
						<option value="Argentine">Argentine</option>
						<option value="Arm�nie">Arm�nie</option>
						<option value="Australie">Australie</option>
						<option value="Autriche">Autriche</option>
						<option value="Azerbaidjan">Azerbaidjan</option>
						<option value="Bahamas">Bahamas</option>
						<option value="Bangladesh">Bangladesh</option>
						<option value="Barbade">Barbade</option>
						<option value="Bahrein">Bahrein</option>
						<option value="Belgique">Belgique</option>
						<option value="Belize">Belize</option>
						<option value="B�nin">B�nin</option>
						<option value="Bermudes">Bermudes</option>
						<option value="Bi�lorussie">Bi�lorussie</option>
						<option value="Bolivie">Bolivie</option>
						<option value="Botswana">Botswana</option>
						<option value="Bhoutan">Bhoutan</option>
						<option value="Bosnie Herz�govine">Bosnie Herz�govine</option>
						<option value="Bresil">Br�sil</option>
						<option value="Brunei">Brunei</option>
						<option value="Bulgarie">Bulgarie</option>
						<option value="Burkina Faso">Burkina Faso</option>
						<option value="Burundi">Burundi</option>
						<option value="Caiman">Ca�man</option>
						<option value="Cambodge">Cambodge</option>
						<option value="Cameroun">Cameroun</option>
						<option value="Canada">Canada</option>
						<option value="Canaries">Canaries</option>
						<option value="Cap Vert">Cap Vert</option>
						<option value="Chili">Chili</option>
						<option value="Chine">Chine</option>
						<option value="Chypre">Chypre</option>
						<option value="Colombie">Colombie</option>
						<option value="Comores">Comores</option>
						<option value="Congo_democratique">R�publique
							D�mocratique du Congo</option>
						<option value="Cook">�les Cook</option>
						<option value="Cor�e du Nord">Cor�e du Nord</option>
						<option value="Cor�e du Sud">Cor�e du Sud</option>
						<option value="Costa Rica">Costa Rica</option>
						<option value="C�te d'Ivoire">C�te d'Ivoire</option>
						<option value="Croatie">Croatie</option>
						<option value="Cuba">Cuba</option>
						<option value="Danemark">Danemark</option>
						<option value="Djibouti">Djibouti</option>
						<option value="Dominique">Dominique</option>
						<option value="Egypte">Egypte</option>
						<option value="Emirats Arabes Unis">Emirats Arabes Unis</option>
						<option value="Equateur">Equateur</option>
						<option value="Erythr�e">Erythr�e</option>
						<option value="Espagne">Espagne</option>
						<option value="Estonie">Estonie</option>
						<option value="Etats Unis">Etats Unis</option>
						<option value="Ethiopie">Ethiopie</option>
						<option value="Falkland">Falkland</option>
						<option value="Feroe">Feroe</option>
						<option value="Fidji">Fidji</option>
						<option value="Finlande">Finlande</option>
						<option value="France">France</option>
						<option value="Gabon">Gabon</option>
						<option value="Gambie">Gambie</option>
						<option value="Georgie">Georgie</option>
						<option value="Ghana">Ghana</option>
						<option value="Gibraltar">Gibraltar</option>
						<option value="Gr�ce">Gr�ce</option>
						<option value="Grenade">Grenade</option>
						<option value="Gro�nland">Gro�nland</option>
						<option value="Guadeloupe">Guadeloupe</option>
						<option value="Guam">Guam</option>
						<option value="Guatemala">Guatemala</option>
						<option value="Guernesey">Guernesey</option>
						<option value="Guin�e">Guin�e</option>
						<option value="Guin�e Bissau">Guin�e Bissau</option>
						<option value="Guin�e Equatoriale">Guin�e Equatoriale</option>
						<option value="Guyana">Guyana</option>
						<option value="Guyane Fran�aise ">Guyane Fran�aise</option>
						<option value="Ha�ti">Ha�ti</option>
						<option value="Hawaii">Hawaii</option>
						<option value="Honduras">Honduras</option>
						<option value="Hong Kong">Hong Kong</option>
						<option value="Hongrie">Hongrie</option>
						<option value="Inde">Inde</option>
						<option value="Indon�sie">Indon�sie</option>
						<option value="Iran">Iran</option>
						<option value="Iraq">Iraq</option>
						<option value="Irlande">Irlande</option>
						<option value="Islande">Islande</option>
						<option value="Isra�l">Isra�l</option>
						<option value="Italie">italie</option>
						<option value="Jama�que">Jama�que</option>
						<option value="Jan Mayen">Jan Mayen</option>
						<option value="Japon">Japon</option>
						<option value="Jersey">Jersey</option>
						<option value="Jordanie">Jordanie</option>
						<option value="Kazakhstan">Kazakhstan</option>
						<option value="Kenya">Kenya</option>
						<option value="Kirghizstan">Kirghizistan</option>
						<option value="Kiribati">Kiribati</option>
						<option value="Koweit">Koweit</option>
						<option value="Laos">Laos</option>
						<option value="Lesotho">Lesotho</option>
						<option value="Lettonie">Lettonie</option>
						<option value="Liban">Liban</option>
						<option value="Lib�ria">Lib�ria</option>
						<option value="Liechtenstein">Liechtenstein</option>
						<option value="Lituanie">Lituanie</option>
						<option value="Luxembourg">Luxembourg</option>
						<option value="Lybie">Lybie</option>
						<option value="Macao">Macao</option>
						<option value="Mac�doine">Mac�doine</option>
						<option value="Madagascar">Madagascar</option>
						<option value="Mad�re">Mad�re</option>
						<option value="Malaisie">Malaisie</option>
						<option value="Malawi">Malawi</option>
						<option value="Maldives">Maldives</option>
						<option value="Mali">Mali</option>
						<option value="Malte">Malte</option>
						<option value="Man">Man</option>
						<option value="�les Mariannes du Nord">�les Mariannes du Nord</option>
						<option value="Maroc">Maroc</option>
						<option value="Marshall">Marshall</option>
						<option value="Martinique">Martinique</option>
						<option value="Maurice">Maurice</option>
						<option value="Mauritanie">Mauritanie</option>
						<option value="Mayotte">Mayotte</option>
						<option value="Mexique">Mexique</option>
						<option value="Micron�sie">Micron�sie</option>
						<option value="Midway">Midway</option>
						<option value="Moldavie">Moldavie</option>
						<option value="Monaco">Monaco</option>
						<option value="Mongolie">Mongolie</option>
						<option value="Montserrat">Montserrat</option>
						<option value="Mozambique">Mozambique</option>
						<option value="Namibie">Namibie</option>
						<option value="Nauru">Nauru</option>
						<option value="N�pal">N�pal</option>
						<option value="Nicaragua">Nicaragua</option>
						<option value="Niger">Niger</option>
						<option value="Nig�ria">Nig�ria</option>
						<option value="Niue">Niue</option>
						<option value="Norfolk">Norfolk</option>
						<option value="Norv�ge">Norv�ge</option>
						<option value="Nouvelle Cal�donie">Nouvelle Cal�donie</option>
						<option value="Nouvelle Z�lande">Nouvelle Z�lande</option>
						<option value="Sultanat d'Oman">Sultanat d'Oman</option>
						<option value="Ouganda">Ouganda</option>
						<option value="Ouzb�kistan">Ouzb�kistan</option>
						<option value="Pakistan">Pakistan</option>
						<option value="Palau">Palau</option>
						<option value="Palestine">Palestine</option>
						<option value="Panama">Panama</option>
						<option value="Papouasie Nouvelle Guin�e">Papouasie Nouvelle Guin�e
						</option>
						<option value="Paraguay">Paraguay</option>
						<option value="Pays Bas">Pays Bas</option>
						<option value="P�rou">P�rou</option>
						<option value="Philippines">Philippines</option>
						<option value="Pologne">Pologne</option>
						<option value="Polyn�sie">Polyn�sie</option>
						<option value="Porto_Rico">Porto_Rico</option>
						<option value="Portugal">Portugal</option>
						<option value="Qatar">Qatar</option>
						<option value="R�publique Dominicaine">R�publique Dominicaine
						</option>
						<option value="R�publique Tch�que">R�publique Tch�que</option>
						<option value="R�union">R�union</option>
						<option value="Roumanie">Roumanie</option>
						<option value="Royaume_Uni">Royaume_Uni</option>
						<option value="Russie">Russie</option>
						<option value="Rwanda">Rwanda</option>
						<option value="Sahara Occidental">Sahara Occidental</option>
						<option value="Sainte Lucie">Sainte Lucie</option>
						<option value="Saint Marin">Saint Marin</option>
						<option value="Salomon">Salomon</option>
						<option value="Salvador">Salvador</option>
						<option value="�les Samoa Occidentales">�les Samoa Occidentales</option>
						<option value="�les Samoa Am�ricaines">�les Samoa Am�ricaines</option>
						<option value="Sao Tome et Principe">Sao Tome et Principe
						</option>
						<option value="S�n�gal">S�n�gal</option>
						<option value="Seychelles">Seychelles</option>
						<option value="Sierra L�one">Sierra L�one</option>
						<option value="Singapour">Singapour</option>
						<option value="Slovaquie">Slovaquie</option>
						<option value="Slov�nie">Slov�nie</option>
						<option value="Somalie">Somalie</option>
						<option value="Soudan">Soudan</option>
						<option value="Sri Lanka">Sri Lanka</option>
						<option value="Su�de">Su�de</option>
						<option value="Suisse">Suisse</option>
						<option value="Surinam">Surinam</option>
						<option value="Swaziland">Swaziland</option>
						<option value="Syrie">Syrie</option>
						<option value="Tadjikistan">Tadjikistan</option>
						<option value="Ta�wan">Ta�wan</option>
						<option value="Tonga">Tonga</option>
						<option value="Tanzanie">Tanzanie</option>
						<option value="Tchad">Tchad</option>
						<option value="Tha�lande">Tha�lande</option>
						<option value="Tibet">Tibet</option>
						<option value="Timor Oriental">Timor_Oriental</option>
						<option value="Togo">Togo</option>
						<option value="Trinit� et Tobago">Trinit� et Tobago</option>
						<option value="Tristan da cunha">Tristan de cuncha</option>
						<option value="Tunisie">Tunisie</option>
						<option value="Turkm�nistan">Turkm�nistan</option>
						<option value="Turquie">Turquie</option>
						<option value="Ukraine">Ukraine</option>
						<option value="Uruguay">Uruguay</option>
						<option value="Vanuatu">Vanuatu</option>
						<option value="Vatican">Vatican</option>
						<option value="V�n�zuela">V�n�zuela</option>
						<option value="�les Vierges Am�ricaines">�les Vierges Am�ricaines</option>
						<option value="�les Vierges Britanniques">�les Vierges Britanniques
						</option>
						<option value="Vietnam">Vietnam</option>
						<option value="Wake">Wake</option>
						<option value="Wallis et Futuma">Wallis et Futuma</option>
						<option value="Y�men">Y�men</option>
						<option value="Yougoslavie">Yougoslavie</option>
						<option value="Zambie">Zambie</option>
						<option value="Zimbabw�">Zimbabw�</option>
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