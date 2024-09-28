package source.model.unite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import source.model.base.Connexion;
import source.model.centre.Centre;

public class UniteOeuvre {
    private int id;
    private String libele;

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
    
}
