<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Affichage des charges</title>
</head>
<body>
    <h1>Résumé de la charge ajoutée</h1>

    <!-- Affichage des attributs Rubrique, Unite et Nature -->
    <div>
        <strong>Rubrique :</strong> <%= request.getAttribute("rubrique") %>
    </div>
    <div>
        <strong>Unité d'oeuvre :</strong> <%= request.getAttribute("unite") %>
    </div>
    <div>
        <strong>Nature de la charge :</strong> 
        <%= request.getAttribute("nature").equals("0") ? "Fixe" : "Variable" %>
    </div>

    <!-- Affichage des centres et des valeurs associées (prix et pourcentage) -->
    <h2>Informations des centres</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Centre</th>
                <th>Prix</th>
                <th>Pourcentage</th>
            </tr>
        </thead>
        <tbody>
            <%
                Map<Integer, Map<String, String>> centreValues = (Map<Integer, Map<String, String>>) request.getAttribute("centreValues");
                for (Map.Entry<Integer, Map<String, String>> entry : centreValues.entrySet()) {
                Integer centreId = entry.getKey();
                Map<String, String> values = entry.getValue();
                String libele = values.get("libele");
                String prix = values.get("prix");
                String pourcentage = values.get("pourcentage");
            %>
                <tr>
                    <td><%= libele %></td>
                    <td><%= prix %></td>
                    <td><%= pourcentage %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <button>Valider</button>
    <button>Annuler</button>
</body>
</html>
