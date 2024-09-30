package source.model.centre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import source.model.base.Connexion;

public class Centre {
    private int id;
    private String libele;

    public Centre() {

    }

    public Centre(String libele) {
        this.setLibele(libele);
    }

    public Centre(int id, String libele) {
        this.setId(id);
        this.setLibele(libele);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getLibele() {
        return libele;
    }

    public void getById(int id) throws Exception {
        Connexion con = new Connexion();
        String sql = "SELECT * FROM centre WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.setId(rs.getInt("id"));
                this.setLibele(rs.getString("libele"));
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
    }

    public void create() throws Exception {
        Connexion con = new Connexion();
        Connection conn = null;
        PreparedStatement stmtCentre = null;
        PreparedStatement stmtCentreCharge = null;

        try {
            conn = con.dbConnect();
            conn.setAutoCommit(false); // Début de la transaction

            // Insertion dans la table centre
            String sqlCentre = "INSERT INTO centre (libele) VALUES (?)";
            stmtCentre = conn.prepareStatement(sqlCentre, Statement.RETURN_GENERATED_KEYS);
            stmtCentre.setString(1, this.libele);
            stmtCentre.executeUpdate();

            // Récupérer l'ID du nouveau centre inséré
            ResultSet generatedKeys = stmtCentre.getGeneratedKeys();
            int centreId = -1;
            if (generatedKeys.next()) {
                centreId = generatedKeys.getInt(1);
            }

            // Insertion dans la table centre_charge pour chaque charge
            String sqlCentreCharge = "INSERT INTO centre_charge (id_centre, id_charge, prix, pourcentage) VALUES (?, ?, 0, 0)";
            stmtCentreCharge = conn.prepareStatement(sqlCentreCharge);

            // Supposons que vous avez une méthode qui récupère tous les ID de charge
            List<Integer> chargeIds = getAllChargeIds(conn); // Cette méthode doit être implémentée pour retourner tous
                                                             // les IDs de charge
            for (int chargeId : chargeIds) {
                stmtCentreCharge.setInt(1, centreId);
                stmtCentreCharge.setInt(2, chargeId);
                stmtCentreCharge.executeUpdate();
            }

            conn.commit(); // Valider la transaction
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Annuler la transaction en cas d'erreur
            }
            throw e;
        } finally {
            // Fermer les ressources
            if (stmtCentreCharge != null)
                stmtCentreCharge.close();
            if (stmtCentre != null)
                stmtCentre.close();
            if (conn != null)
                conn.close();
        }
    }

    private List<Integer> getAllChargeIds(Connection conn) throws SQLException {
        List<Integer> chargeIds = new ArrayList<>();
        String sql = "SELECT id FROM charge"; // Adapter selon votre table charge
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            chargeIds.add(rs.getInt("id"));
        }
        return chargeIds;
    }

    public boolean update() throws Exception {
        Connexion con = new Connexion();
        String sql = "UPDATE centre SET libele = ? WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {

            stmt.setString(1, this.libele);
            stmt.setInt(2, this.id);
            int rowsUpdated = stmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean delete() throws Exception {
        Connexion con = new Connexion();
        String sql = "DELETE FROM centre WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setInt(1, this.id);
            int rowsDeleted = stmt.executeUpdate();
            conn.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
    }

    public List<Centre> getAll() throws Exception {
        Connexion con = new Connexion();
        List<Centre> centres = new ArrayList<>();
        String sql = "SELECT * FROM centre";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setLibele(rs.getString("libele"));
                centres.add(centre);
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return centres;
    }

    public static List<Centre> getAllStatic() throws Exception {
        Connexion con = new Connexion();
        List<Centre> centres = new ArrayList<>();
        String sql = "SELECT * FROM centre";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setLibele(rs.getString("libele"));
                centres.add(centre);
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return centres;
    }
}
