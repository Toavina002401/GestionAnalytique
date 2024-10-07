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
    private int idType;

    public Centre() {

    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdType() {
        return idType;
    }

    public Centre(String libele) {
        this.setLibele(libele);
    }

    public Centre(String libele, int typeCentre) {
        this.setLibele(libele);
        this.setIdType(typeCentre);
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

    public int createCentre() throws Exception {
        Connexion con = new Connexion();
        Connection conn = null;
        PreparedStatement stmtCentre = null;
        int centreId = -1;

        try {
            conn = con.dbConnect();
            conn.setAutoCommit(false); // Début de la transaction

            // Insertion dans la table centre
            String sqlCentre = "INSERT INTO centre (libele, id_type) VALUES (?, ?)";
            stmtCentre = conn.prepareStatement(sqlCentre, Statement.RETURN_GENERATED_KEYS);
            stmtCentre.setString(1, this.libele);
            stmtCentre.setInt(2, this.idType);
            stmtCentre.executeUpdate();

            // Récupérer l'ID du nouveau centre inséré
            ResultSet generatedKeys = stmtCentre.getGeneratedKeys();
            if (generatedKeys.next()) {
                centreId = generatedKeys.getInt(1); // Obtenir l'ID généré pour le centre
            }

            if (centreId == -1) {
                throw new SQLException("L'insertion du centre a échoué, aucun ID généré.");
            }

            conn.commit(); // Valider la transaction
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Annuler la transaction en cas d'erreur
            }
            throw e;
        } finally {
            // Fermer les ressources
            if (stmtCentre != null)
                stmtCentre.close();
            if (conn != null)
                conn.close();
        }

        return centreId; // Retourner l'ID du nouveau centre
    }

    public void createCentreCharge(int centreId, List<Integer> chargeIds) throws Exception {
        Connexion con = new Connexion();
        Connection conn = null;
        PreparedStatement stmtCentreCharge = null;

        try {
            conn = con.dbConnect();
            conn.setAutoCommit(false); // Début de la transaction

            // Insertion dans la table centre_charge pour chaque charge
            String sqlCentreCharge = "INSERT INTO centre_charge (id_centre, id_charge, prix, pourcentage) VALUES (?, ?, 0, 0)";
            stmtCentreCharge = conn.prepareStatement(sqlCentreCharge);

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
            if (conn != null)
                conn.close();
        }
    }

    public void create() throws Exception {
        try {
            int centreId = createCentre(); // Créer un nouveau centre et obtenir son ID
            List<Integer> chargeIds = getAllChargeIds(); // Obtenir la liste des ID de charges à associer au centre

            // Insertion des charges dans centre_charge
            createCentreCharge(centreId, chargeIds);

            System.out.println("Centre et charges créés avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Integer> getAllChargeIds() throws Exception {
        List<Integer> chargeIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connexion à la base de données
            Connexion con = new Connexion();
            conn = con.dbConnect();

            // Préparer la requête pour récupérer tous les ID de charge
            String sql = "SELECT id FROM charge"; // Remplace "charge" par le nom correct de ta table
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Ajouter les résultats à la liste
            while (rs.next()) {
                chargeIds.add(rs.getInt("id"));
            }
        } finally {
            // Fermer les ressources
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
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
                centre.setIdType(rs.getInt("id_type"));
                centres.add(centre);
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return centres;
    }

    public List<Centre> getAll(int idTypeCentre) throws Exception {
        Connexion con = new Connexion();
        List<Centre> centres = new ArrayList<>();
        String sql = "SELECT * FROM centre where id_type = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, idTypeCentre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setLibele(rs.getString("libele"));
                centre.setIdType(rs.getInt("id_type"));
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

    public List<Centre> getByIdType(int idType) throws Exception {
        Connexion con = new Connexion();
        List<Centre> centres = new ArrayList<>();
        String sql = "SELECT * FROM centre where id_type = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {

            stmt.setInt(1, idType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setLibele(rs.getString("libele"));
                centre.setIdType(rs.getInt("id_type"));
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
