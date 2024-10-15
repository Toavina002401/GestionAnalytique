package source.model.centreCharge;

import source.model.charge.Charge;
import source.model.centre.Centre;
import source.model.base.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentreCharge {
    private int id;
    private Centre centre;
    private Charge charge;
    private double prix;
    private double pourcentage;

    public CentreCharge() {
    }

    public CentreCharge(int idCentre, int idCharge, double prix, double pourcentage) throws Exception {
        this.setCentre(idCharge);
        this.setCharge(idCharge);
        this.setPrix(prix);
        this.setPourcentage(pourcentage);
    }

    public CentreCharge(int id, Centre centre, Charge charge, double prix, double pourcentage) {
        this.setId(id);
        this.setCentre(centre);
        this.setCharge(charge);
        this.setPrix(prix);
        this.setPourcentage(pourcentage);
    }

    // Getters et setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public void setCentre(int id) throws Exception {
        Centre c = new Centre();
        c.getById(id);
        this.setCentre(c);
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public void setCharge(int id) throws Exception {
        Charge ch = new Charge();
        ch.getById(id);
        this.setCharge(ch);
    }

    public Charge getCharge() {
        return charge;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void create() throws Exception {
        Connexion con = new Connexion();
        String sql = "INSERT INTO centre_charge (id_centre, id_charge, prix, pourcentage) VALUES (?, ?, ?, ?)";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, this.centre.getId());
            stmt.setInt(2, this.charge.getId());
            stmt.setDouble(3, this.prix);
            stmt.setDouble(4, this.pourcentage);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }

    public boolean update() throws Exception {
        Connexion con = new Connexion();
        String sql = "UPDATE centre_charge SET prix = ?, pourcentage = ? WHERE id_charge = ? and id_centre= ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(4, this.centre.getId());
            stmt.setInt(3, this.charge.getId());
            stmt.setDouble(1, this.prix);
            stmt.setDouble(2, this.pourcentage);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }

    public boolean delete() throws Exception {
        Connexion con = new Connexion();
        String sql = "DELETE FROM centre_charge WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, this.id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }

    public List<CentreCharge> getAll() throws Exception {
        Connexion con = new Connexion();
        List<CentreCharge> centreCharges = new ArrayList<>();
        String sql = "SELECT * FROM centre_charge";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CentreCharge centreCharge = new CentreCharge();
                centreCharge.setId(rs.getInt("id"));
                centreCharge.setCentre(rs.getInt("id_centre"));
                centreCharge.setCharge(rs.getInt("id_charge"));
                centreCharge.setPrix(rs.getDouble("prix"));
                centreCharge.setPourcentage(rs.getDouble("pourcentage"));
                centreCharges.add(centreCharge);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
        return centreCharges;
    }

    public void getById(int id) throws Exception {
        Connexion con = new Connexion();
        String sql = "SELECT * FROM centre_charge WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.setId(rs.getInt("id"));
                this.setCentre(rs.getInt("id_centre"));
                this.setCharge(rs.getInt("id_charge"));
                this.setPrix(rs.getDouble("prix"));
                this.setPourcentage(rs.getDouble("pourcentage"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }

    public double[] getSommePrixParCentre(char variable) throws Exception {
        Connexion con = new Connexion();
        String sql = "SELECT SUM(cc.prix) AS somme_prix " +
                "FROM centre_charge cc " +
                "JOIN charge ch ON cc.id_charge = ch.id " +
                "WHERE ch.nature = ? " +
                "GROUP BY cc.id_centre";

        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);

        List<Double> prixList = new ArrayList<>();

        try {
            stmt.setString(1, String.valueOf(variable));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prixList.add(rs.getDouble("somme_prix"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

        // Vérification de la taille de la liste avant conversion
        if (prixList.isEmpty()) {
            return new double[Centre.getAllStatic().size()];
        }

        double[] prixArray = new double[prixList.size()];
        for (int i = 0; i < prixList.size(); i++) {
            prixArray[i] = prixList.get(i);
        }
        return prixArray;
    }

    public boolean deleteFromCharge(int idCharge) throws Exception {
        Connexion con = new Connexion();
        String sql = "DELETE FROM centre_charge WHERE id_charge = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, idCharge);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }

}
