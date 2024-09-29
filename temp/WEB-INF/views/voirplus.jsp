<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,source.model.unite.*,source.model.centre.*,source.model.charge.*,source.model.centreCharge.*" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Détails des Charges et CentreCharges</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h1>Détails des Charges</h1>
    <table>
        <thead>
            <tr>
                <th rowspan="2">Rubrique</th>
                <th rowspan="2">Total Prix</th>
                <th rowspan="2">Unité d'oeuvre</th>
                <th rowspan="2">Nature</th>
                <%
                // Récupérer les centres
                List<Centre> centres = (List<Centre>) request.getAttribute("listeCentre");
                // Boucler sur les centres pour créer les en-têtes
                for (Centre centre : centres) {
                %>
                    <th colspan="3"><%= centre.getLibele() %></th>
                <%
                }
                %>
            </tr>
            <tr>
                <%
                // Boucler de nouveau pour les sous-en-têtes
                for (Centre centre : centres) {
                %>
                    <th>%</th>
                    <th>FIXE</th>
                    <th>VARIABLE</th>
                <%
                }
                %>
            </tr>
        </thead>
        <tbody>
            <%
            // Récupérer les objets Charge et CentreCharge à partir du modèle
            List<Charge> allCharge = (List<Charge>) request.getAttribute("allCharge");
            List<CentreCharge> allCentreCharge = (List<CentreCharge>) request.getAttribute("allCentreCharge");

            // Itérer à travers allCharge pour afficher les détails
            for (Charge charge : allCharge) {
                // Initialiser les valeurs à zéro pour chaque charge
                double totalPrix = charge.getPrix(); // On suppose que chaque charge a un prix total
                String uniteOeuvre = charge.getUniteOeuvre().getLibele(); // Assurez-vous que cette méthode existe
                char nature = charge.getNature(); // Nature de la charge

                // Début de la ligne pour la charge
            %>
                <tr>
                    <td><%= charge.getRubrique() %></td>
                    <td><%= totalPrix %></td>
                    <td><%= uniteOeuvre %></td>
                    <td><%= nature %></td>
                    <%
                    // Itérer sur les centres pour afficher les valeurs des CentreCharge
                    for (Centre centre : centres) {
                        // Chercher la CentreCharge correspondante
                        CentreCharge centreCharge = null;
                        for (CentreCharge cc : allCentreCharge) {
                            if (cc.getCentre().getId() == centre.getId() && cc.getCharge().getId() == charge.getId()) {
                                centreCharge = cc;
                                break;
                            }
                        }
                        // Si la CentreCharge est trouvée, afficher ses valeurs, sinon laisser vide
                        if (centreCharge != null) {
                    %>
                        <td><%= centreCharge.getPourcentage() %></td>
                        <td>
                            <%
                                if(nature=='F'){
                                    out.print(centreCharge.getPrix());
                                }else{
                                    out.print("-");
                                } 
                            %>
                        </td>
                        <td>
                            <%
                                if(nature=='V'){
                                    out.print(centreCharge.getPrix());
                                }else{
                                    out.print("-");
                                } 
                            %>
                        </td> <!-- Calculer la variable -->
                    <%
                        } else {
                    %>
                        <td></td>
                        <td></td>
                        <td></td>
                    <%
                        }
                    }
                    %>
                </tr>
            <%
            }
            %>
        </tbody>
        <tfoot>
            <tr>
                <td>Total</td>
                <td><%= (new Charge()).totalPrixCharge() %></td>
                <td></td>
                <td></td>
                <%
                    double[] prixVariable = (double[])request.getAttribute("totalVariableCentre");
                    for (int i = 0; i < centres.size(); i++) {  
                %>
                <td colspan="3" style="text-align: center;">
                    <%
                        try{
                            out.print(prixVariable[i]);
                        } catch (Exception e) {
                            out.print("Erreur: " + e.getMessage());
                        }
                    %>
                </td>
                <% } %>
            </tr>
        </tfoot>
    </table>
    </body>
    </html>
