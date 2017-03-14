<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AGRIOTE</title>
        <style>
            body {
                text-align: center;
            }
            
            #candidat-form{
                margin: auto;
                border-style: solid;
                border-width: thin;
                width: 600px;
                height: 600px;
                background-color: white;
                position: relative;
            }
            #infos-session{
                margin: 10px;
                padding-bottom: 40px;
                border-bottom: thin black solid;
            }
            #content-form{
                text-align: left;
                margin-top: 20px;
                margin-left: 20px;
            }
            #backlink{
                bottom: 5px;
                right: 5px;
                position: absolute;
            }
            
        </style>
    </head>
    <body>
        <div id="candidat-form">
        <h1>//Nom formation//</h1>
        <div id="infos-session">
                Autem utilia admovente mariti haec cum reducere cum exitium ut deberet propositum in 
                lenitate praeceps stimulos scrutanda ad rettulimus fortunas suadendo trudebat lenitate 
                admovente trudebat exitium stimulos admovente cum factitasse imperatoris admovente 
                propositum utilia fortunas lenitate erga cum multa potius feminea truculenti cum 
                imperatoris factitasse ut ut exitium fortunas similia
        </div>
        <div id="content-form">
            <form method="POST" action="postuler">
                <p>
                    <label style="vertical-align: top"for="file">Votre CV : </label>
                    <input style="vertical-align: top" type="file" value="cv..." id="cv" name="cv" disabled/><br><br>
                </p>
                <p>
                    <label style="vertical-align: top"for="motivations">Vos motivations : </label> 
                    <textarea style="vertical-align: top" name="motivations" rows="4" cols="40" disabled>...</textarea><br>
                </p>
                
                  
                <div style="text-align: center;">
                    <br>
                    <input style="display:none;" type="text" name="idSessionFormation" value="${requestScope.idsession}" disabled/>
                    <input type="submit" name="submit" value="Envoyer candidature"/> 
                    <input type="reset" name="reset" value="Effacer" />
                </div>
            </form>
        </div>
        <br>
        <br>
        <div id="backlink"> <a href="<c:url value="sessionsOuvertes" />">Annuler - Retour à la liste des sessions</a></div>
        </div>
    </body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</html>
