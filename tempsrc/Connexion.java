package source.model.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public Connection dbConnect() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/gestion_analytique"; 
        String username = "root";  
        String password = "patrick"; 
        
        try {
            // Retourner la connexion si elle réussit
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            // Afficher un message d'erreur et relancer l'exception
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
