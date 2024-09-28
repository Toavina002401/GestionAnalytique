package source.model.unite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import source.model.base.Connexion;

public class UniteOeuvre {
    private int id;
    private String libele;

    public UniteOeuvre() {}

    public UniteOeuvre(String libele) {
        this.setLibele(libele);
    }

    public UniteOeuvre(int id, String libele) {
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
        UniteOeuvre unite = null;
        String sql = "SELECT * FROM unite_oeuvre WHERE id = ?";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                unite = new UniteOeuvre();
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
        String sql = "INSERT INTO unite_oeuvre (libele) VALUES (?)";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {

            stmt.setString(1, this.libele);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
    }

    public boolean update() throws Exception {
        Connexion con = new Connexion();
        String sql = "UPDATE unite_oeuvre SET libele = ? WHERE id = ?";
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
        String sql = "DELETE FROM unite_oeuvre WHERE id = ?";
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

    public List<UniteOeuvre> getAll() throws Exception {
        Connexion con = new Connexion();
        List<UniteOeuvre> uo = new ArrayList<>();
        String sql = "SELECT * FROM unite_oeuvre";
        Connection conn = con.dbConnect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UniteOeuvre u = new UniteOeuvre();
                u.setId(rs.getInt("id"));
                u.setLibele(rs.getString("libele"));
                uo.add(u);
            }
            conn.close();
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return uo;
    }

}
