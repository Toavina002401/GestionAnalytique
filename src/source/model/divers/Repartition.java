package source.model.divers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import source.model.base.Connexion;
import source.model.centre.Centre;

public class Repartition {

    private String nomCentre;
    private double coutDirecte;
    private double cle;
    private double repartition;
    private double coutTotal;
    private Centre centre;

    public double getRepartition() {
        return repartition;
    }

    public void setRepartition(double repartition) {
        this.repartition = repartition;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public String getNomCentre() {
        return nomCentre;
    }

    public void setNomCentre(String nomCentre) {
        this.nomCentre = nomCentre;
    }

    public double getCoutDirecte() {
        return coutDirecte;
    }

    public void setCoutDirecte(double coutDirecte) {
        this.coutDirecte = coutDirecte;
    }

    public double getCle() {
        return cle;
    }

    public void setCle(double cle) {
        this.cle = cle;
    }

    public Map<String, Object> calculerCles(int idTypeCentre,int ... qt) throws Exception {
        Centre centre = new Centre();

        List<Repartition> listeRepartition = new ArrayList<>();
        double total = 0;

        List<Centre> listeCentre = centre.getAll(idTypeCentre);
        for (Centre c : listeCentre) {
            if (c.getIdType() == 2) {
                Repartition repartition = new Repartition();
                double ceCentre = this.getSommePrixParCentre(c.getId());
                repartition.setCoutDirecte(ceCentre);
                repartition.setNomCentre(c.getLibele());
                repartition.setCentre(c);
                listeRepartition.add(repartition);
                // repartition.set
                total += ceCentre;
            }
        }
        // Sommer tous les cout indirecte:
        List<Centre> listeCentreStructurel = centre.getAll(1);
        double totalARepartir = 0;
        String nomStructurel = "";
        for (Centre centre2 : listeCentreStructurel) {
            totalARepartir += this.getSommePrixParCentre(centre2.getId());
            nomStructurel += centre2.getLibele() + "/";
        }

        if (nomStructurel.length() > 0) {
            nomStructurel = nomStructurel.substring(0, nomStructurel.length() - 1);
        }
        double totalCoutDirecte = 0;
        double totalCoutTotal = 0;

        // calculer les cless maintenant:
        for (Repartition rep : listeRepartition) {
            double cle = (rep.getCoutDirecte() / total) * 100;
            rep.setRepartition((rep.getCoutDirecte() / total) * totalARepartir);
            rep.setCle(cle);
            rep.setCoutTotal(rep.getCoutDirecte() + rep.getRepartition());
            totalCoutDirecte += rep.getCoutDirecte();
            totalCoutTotal += rep.getCoutTotal();
        }
        double quantite = 100000;
        if (qt.length > 0) {
            quantite = qt[0];
        }
        double seuil = totalCoutTotal / quantite;
        double valeurAjouter = seuil * 0.02;
        double pu = seuil + valeurAjouter;
        double benefice = valeurAjouter * quantite; 

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("nomStructurel", nomStructurel);
        reponse.put("listeRepartition", listeRepartition);
        reponse.put("totalCoutDirecte", totalCoutDirecte);
        reponse.put("totalCoutTotal", totalCoutTotal);
        reponse.put("totalARepartir", totalARepartir);
        reponse.put("quantite", quantite);
        reponse.put("seuil", seuil);
        reponse.put("valeurAjouter", valeurAjouter);
        reponse.put("totalBenefice", benefice);
        reponse.put("pu", pu);
        
        

        return reponse;

    }

    

    public void sommeCentreParType(int idTypeCentre) {
    }

    public double getSommePrixParCentre(int id_centre) throws Exception {
        Connexion con = new Connexion();
        String sql = "SELECT SUM(cc.prix) AS somme_prix " +
                "FROM centre_charge cc " +
                "JOIN charge ch ON cc.id_charge = ch.id " +
                "AND id_centre = ?";

        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);

        double reponse = 0;

        try {
            stmt.setInt(1, id_centre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // prixList.add(rs.getDouble("somme_prix"));
                reponse = rs.getDouble("somme_prix");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

        return reponse;
    }

}
