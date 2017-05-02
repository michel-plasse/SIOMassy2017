<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creation du projet</title>
    </head>
    <body>
        <h1>Creation du projet</h1>
        <table border=0>
            <form method="post">
                <tr>
                    <td>Session_formation : </td>
                    <td>
                        <select name="idSession">
                            <option value="1" selected="selected">BTS SIO</option>
                            <option value="2">BTS </option>
                        </select>
                        ${idSession}
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label for="sujet">Sujet :</label>
                    </td>
                    <td>
                        <input type="text" name="sujet" />
                        ${sujet}
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="date limite">Date limite :</label>
                    </td>
                    <td>
                        <input type="date" name="dateLimite"/>
                        ${dateLimite}
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="description">
                            Description :
                        </label>
                    </td>
                    <td>
                        <textarea name="description" cols="60" rows="10">
                                   
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
        </table>
        ${message}
    </body>
</html>
