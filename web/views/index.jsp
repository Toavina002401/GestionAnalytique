<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*" %>
<%
    List<UniteOeuvre> uniteOeuvre = (List<UniteOeuvre>)request.getAttribute("listeUniteOeuvre");
    List<Centre> centres = (List<Centre>)request.getAttribute("listeCentre");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projet Farine</title>
</head>
<body>
    <center>
        <h1>
            <a href="voir">Voir Tout</a>
        </h1>
        <h1>Insertion charge possible</h1>

        <form action="addCharge" method="get">
            <div>
                <label for="rubrique">Rubrique</label>
                <input type="text" name="rubrique" id="rubrique">
            </div>
            <div>
                <label for="unite">Unite d'oeuvre</label>
                <select name="unite" id="unite">
                    <% for (UniteOeuvre unite : uniteOeuvre) { %>
                        <option value="<%= unite.getId() %>"><%= unite.getLibele() %></option>
                    <% } %>
                </select>
                
                <button onclick="ajouterUnite()">Ajouter unite d'oeuvre</button>
            </div>
            <div>
                <label for="nature">Nature</label>
                <select name="nature" id="nature">
                    <option value="F">Fixe</option>
                    <option value="V">Variable</option>
                </select>
            </div>
            <div>
                <label for="prix">Prix</label>
                <input type="text" name="prix" id="prix">
            </div>
            <% for (Centre centre : centres) { %>
                <div>
                    <label for="<%= centre.getLibele() %>"><%= centre.getLibele() %></label>
                    <input type="text" name="<%= centre.getId() %>_prix" id="<%= centre.getId() %>_prix" placeholder="prix">
                    <input type="text" name="<%= centre.getId() %>_pourcentage" id="<%= centre.getId() %>_pourcentage" placeholder="pourcentage">
                </div>
            <% } %>
            <input type="submit" value="Inserer">
        </form>




        <div id="affUnite">
            <h2>Ajouter unite d'oeuvre</h2>
            <form action="ajoutUnite" method="get">
                <label for="uniteAjout">Nom de l'unite</label>
                <input type="text" name="uniteAjout" id="uniteAjout">
                <input type="submit" value="ajouter">
            </form>
        </div>

        <div id="affAjoutCentre">
            <h2>Ajouter centre</h2>
            <form action="ajoutNouveauCentre" method="get">
                <label for="centreAjout">Nom du centre</label>
                <input type="text" name="centreAjout" id="centreAjout">
                <input type="submit" value="ajouter">
            </form>
        </div>
    </center>
</body>
</html>