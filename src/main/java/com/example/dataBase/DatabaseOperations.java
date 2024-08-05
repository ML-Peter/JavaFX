package com.example.dataBase;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.example.classes.Bibliotheque;
import com.example.classes.Livre;
import java.sql.SQLException;

public class DatabaseOperations {
    public void createTableForClass(Class<?> clazz) {
        try {
            DatabaseSingleton dbSingleton = DatabaseSingleton.getInstance();
            Connection connection = dbSingleton.getConnection();
            Statement statement = connection.createStatement();

            // Création de la table
            StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            createTableSql.append(clazz.getSimpleName()).append(" (");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                createTableSql.append(field.getName()).append(" ");
                if (field.getType() == int.class) {
                    createTableSql.append("INT");
                } else if (field.getType() == String.class) {
                    createTableSql.append("VARCHAR(255)");
                } else {
                    // Ajoutez d'autres types de données si nécessaire
                }
                createTableSql.append(", ");
            }
            createTableSql.delete(createTableSql.length() - 2, createTableSql.length()); // Supprime la dernière virgule
            createTableSql.append(")");

            statement.executeUpdate(createTableSql.toString());
            System.out.println("Table créée avec succès pour la classe " + clazz.getSimpleName());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table", e);
        }
    }

    public static void chargerLivresDepuisBaseDeDonnees(Bibliotheque bibliotheque) {
        try (Connection conn = DatabaseSingleton.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Livre")) {

            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                String auteur = rs.getString("auteur");
                String maisonEdition = rs.getString("maisonEdition");
                String categorie = rs.getString("categorie");

                Livre livre = new Livre(code, nom, auteur, maisonEdition, categorie);
                bibliotheque.ajouterLivre(livre);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des livres depuis la base de données.");
            e.printStackTrace();
        }
    }

    public static void sauvegarderLivresDansBaseDeDonnees(Bibliotheque bibliotheque) {
        try (Connection conn = DatabaseSingleton.getInstance().getConnection();
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM Livre"); // Clear existing records

            for (Livre livre : bibliotheque.getLivres()) {
                String query = "INSERT INTO Livre (code, nom, auteur, maisonEdition, categorie) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, livre.getCode());
                    pstmt.setString(2, livre.getNom());
                    pstmt.setString(3, livre.getAuteur());
                    pstmt.setString(4, livre.getMaisonEdition());
                    pstmt.setString(5, livre.getCategorie());
                    pstmt.executeUpdate();
                }
                System.out.println("Les livres ont été sauvegardés dans la base de données.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la sauvegarde des livres dans la base de données.");
            e.printStackTrace();
        }
    }

    public static void ajouterLivreDansBaseDeDonnees(Livre livre) {
        try (Connection conn = DatabaseSingleton.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO Livre (code, nom, auteur, maisonEdition, categorie) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, livre.getCode());
            pstmt.setString(2, livre.getNom());
            pstmt.setString(3, livre.getAuteur());
            pstmt.setString(4, livre.getMaisonEdition());
            pstmt.setString(5, livre.getCategorie());
            pstmt.executeUpdate();
            System.out.println("Le livre a été ajouté à la base de données.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre dans la base de données.");
            e.printStackTrace();
        }
    }

    public static void supprimerLivreDeBaseDeDonnees(String code) {
        try (Connection conn = DatabaseSingleton.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Livre WHERE code = ?")) {

            pstmt.setString(1, code);
            pstmt.executeUpdate();
            System.out.println("Le livre a été supprimé de la base de données.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du livre de la base de données.");
            e.printStackTrace();
        }
    }

    public static void ajouterLivre(Livre livre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterLivre'");
    }

    public static void mettreAJourLivre(Livre selectedLivre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mettreAJourLivre'");
    }

    public static void supprimerLivre(Livre selectedLivre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerLivre'");
    }

}