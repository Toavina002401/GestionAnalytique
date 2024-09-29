package source.model.charge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.model.base.Connexion;
import source.model.unite.UniteOeuvre;

public class Charge {
    private int id;
    private String rubrique;
    private double prix;
    private char nature;
    private UniteOeuvre uniteOeuvre;

    public Charge() {
    }

    public Charge(int id, String rubrique, double prix, char nature, int idUnite) throws Exception {
        this.setId(id);
        this.setRubrique(rubrique);
        this.setNature(nature);
        this.setUniteOeuvre(idUnite);
    }
    public Charge(int id, String rubrique, double prix, char nature, UniteOeuvre unite) {
        this.setId(id);
        this.setRubrique(rubrique);
        this.setNature(nature);
        this.setUniteOeuvre(unite);
    }


    // Getters et setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public String getRubrique() {
        return rubrique;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPrix(String prix) throws Exception {
        try {
            double v = Double.parseDouble(prix);
            this.setPrix(v);
        } catch (NumberFormatException e) {
            throw new Exception("Le prix ne doit contenir que des chiffres");
        }
    }

    public double getPrix() {
        return prix;
    }

    public void setNature(char nature) {
        this.nature = nature;
    }

    public char getNature() {
        return nature;
    }

    public void setUniteOeuvre(UniteOeuvre uniteOeuvre) {
        this.uniteOeuvre = uniteOeuvre;
    }

    public void setUniteOeuvre(int id) throws Exception {
        UniteOeuvre u = new UniteOeuvre();
        u.getById(id);
        this.setUniteOeuvre(u);
    }

    public void getUniteOeuvre(int id) throws Exception {
        UniteOeuvre uo = new UniteOeuvre();
        uo.getById(id);
        this.uniteOeuvre = uo;
    }

    public UniteOeuvre getUniteOeuvre() throws Exception {
        return uniteOeuvre;
    }

    // CRUD operations

    public void getById(int id) throws Exception {
        Connexion con = new Connexion();
        String sql = "SELECT * FROM charge WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.setId(rs.getInt("id"));
                this.setRubrique(rs.getString("rubrique"));
                this.setPrix(rs.getDouble("prix"));
                this.setNature(rs.getString("nature").charAt(0));
                this.setUniteOeuvre(rs.getInt("id_unite"));
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
    }

    public void create() throws Exception {
        Connexion con = new Connexion();
        String sql = "INSERT INTO charge (rubrique, prix, nature, id_unite) VALUES (?, ?, ?, ?)";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setString(1, this.rubrique);
            stmt.setDouble(2, this.prix);
            stmt.setString(3, String.valueOf(this.nature));
            stmt.setInt(4, this.uniteOeuvre.getId());

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
    }

    public boolean update() throws Exception {
        Connexion con = new Connexion();
        String sql = "UPDATE charge SET rubrique = ?, prix = ?, nature = ?, id_unite = ? WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            stmt.setString(1, this.rubrique);
            stmt.setDouble(2, this.prix);
            stmt.setString(3, String.valueOf(this.nature));
            stmt.setInt(4, this.uniteOeuvre.getId());
            stmt.setInt(5, this.id);

            int rowsUpdated = stmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean delete() throws Exception {
        Connexion con = new Connexion();
        String sql = "DELETE FROM charge WHERE id = ?";
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

    public List<Charge> getAll() throws Exception {
        Connexion con = new Connexion();
        List<Charge> charges = new ArrayList<>();
        String sql = "SELECT * FROM charge";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Charge charge = new Charge();
                charge.setId(rs.getInt("id"));
                charge.setRubrique(rs.getString("rubrique"));
                charge.setPrix(rs.getDouble("prix"));
                charge.setNature(rs.getString("nature").charAt(0));
                charge.setUniteOeuvre(rs.getInt("id_unite"));

                charges.add(charge);
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return charges;
    }

    public double totalPrixCharge() throws Exception{
        double valiny = 0;
        List<Charge> liste = this.getAll();
        for (Charge charge : liste) {
            valiny += charge.getPrix();
        }
        return valiny;
    }
}
