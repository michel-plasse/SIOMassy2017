<%-- 
    Document   : projet
    Created on : 28 mars 2017, 16:03:26
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p><h1> Creation du projet</h1></p>
	<table border=0>
		<form method="post">
                    <tr>
				<td>Session_formation : </td>
				<td><select name="sessionFormation">
						<option value="BTS SIO" selected="selected">BTS SIO</option>
                                                <option value='BTS '>BTS </option>
						
				</select></td>
			</tr>
                        <tr>
                            <td> 
                                        <label for="sujet">Sujet de projet :</label>
                                        <input type="text" name="sujet" />
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="date limite"> Date limite:</label>
                                <input type="datetime-local" name="dateLimite"/>
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <label for="date de creation"> Date de création:</label>
                                <input type="date" name="dateCreation"/>
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <label for="date de fin "> Date de fin:</label>
                                <input type="date" name="dateFin"/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                  <label for="ameliorer">
                                     Description:
                                  </label>
                                  <br />
       
                                     <textarea name="ameliorer" >
                                   
                                     </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Valider" />
                            </td>
                        </tr>
                          <tr>
                            <td>
                                <input type="submit" value="Annuler" />
                            </td>
                        </tr>
        
        
    </body>
</html>
