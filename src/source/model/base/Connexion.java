package source.model.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public Connection dbConnect() throws Exception{
        String jdbcUrl = "jdbc:mysql://localhost:3306/gestion_analytique";
        String username = "root";
        String password = "patrick";

        try {
            // Charger le pilote MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            return connection;

        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur SQL
            throw e; // Relance l'exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Affiche l'erreur de chargement du pilote
        }
        return null; // Si aucune connexion n'a été établie
    }

}
