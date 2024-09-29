package source.model.centre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO centre (libele) VALUES (?)";
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
}
