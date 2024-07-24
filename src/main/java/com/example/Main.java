package com.example;
//import com.example.Bibliotheque;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Bibliotheque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Créer la table 'Livre' si elle n'existe pas
        DatabaseOperations dbOps = new DatabaseOperations();
        dbOps.createTableForClass(Livre.class);

        // Charger les livres depuis la base de données
        chargerLivresDepuisBaseDeDonnees(bibliotheque);

        Scanner scanner = new Scanner(System.in);
        String choix;

        do {
            System.out.println("\n1. Obtenir un livre par son code");
            System.out.println("2. Modifier un livre");
            System.out.println("3. Rechercher un livre par son nom");
            System.out.println("4. Lister les livres par lettre");
            System.out.println("5. Obtenir le nombre total de livres");
            System.out.println("6. Obtenir les livres par catégorie");
            System.out.println("7. Supprimer un livre");
            System.out.println("8. Ajouter un livre");
            System.out.println("9. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.print("Entrez le code du livre : ");
                    String code = scanner.nextLine();
                    Livre livre = bibliotheque.getLivre(code);
                    if (livre != null) {
                        System.out.println("Le livre obtenu est : " + livre.getNom());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;
                case "2":
                    System.out.print("Entrez le code du livre à modifier : ");
                    String codeModif = scanner.nextLine();
                    System.out.print("Entrez le nouveau nom du livre : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez le nouvel auteur du livre : ");
                    String nouvelAuteur = scanner.nextLine();
                    System.out.print("Entrez la nouvelle maison d'édition du livre : ");
                    String nouvelleMaisonEdition = scanner.nextLine();
                    System.out.print("Entrez la nouvelle catégorie du livre : ");
                    String nouvelleCategorie = scanner.nextLine();
                    Livre nouveauLivre = new Livre(codeModif, nouveauNom, nouvelAuteur, nouvelleMaisonEdition,
                            nouvelleCategorie);
                    bibliotheque.modifierLivre(codeModif, nouveauLivre);
                    sauvegarderLivresDansBaseDeDonnees(bibliotheque);
                    break;
                case "3":
                    System.out.print("Entrez le nom du livre à rechercher : ");
                    String nomRecherche = scanner.nextLine();
                    Livre livreRecherche = bibliotheque.rechercherParNom(nomRecherche);
                    if (livreRecherche != null) {
                        System.out.println("Le livre recherché est : " + livreRecherche.getNom());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;
                case "4":
                    System.out.print("Entrez la lettre par laquelle les livres commencent : ");
                    char lettre = scanner.nextLine().charAt(0);
                    var livresParLettre = bibliotheque.listerLivresParLettre(lettre);
                    System.out.println("Les livres commençant par " + lettre + " sont : " + livresParLettre);
                    break;
                case "5":
                    System.out.println("Le nombre total de livres est : " + bibliotheque.nombreDeLivres());
                    break;
                case "6":
                    System.out.print("Entrez la catégorie de livres à afficher : ");
                    String categorie = scanner.nextLine();
                    var livresParCategorie = bibliotheque.livresParCategorie(categorie);
                    System.out.println("Les livres de la catégorie '" + categorie + "' sont : " + livresParCategorie);
                    break;
                case "7":
                    System.out.print("Entrez le code du livre à supprimer : ");
                    String codeSuppr = scanner.nextLine();
                    bibliotheque.supprimerLivre(codeSuppr);
                    supprimerLivreDeBaseDeDonnees(codeSuppr);
                    System.out.println("Le livre avec le code '" + codeSuppr + "' a été supprimé.");
                    break;
                case "8":
                    System.out.print("Entrez le code du livre : ");
                    String codeAjout = scanner.nextLine();
                    System.out.print("Entrez le nom du livre : ");
                    String nomAjout = scanner.nextLine();
                    System.out.print("Entrez l'auteur du livre : ");
                    String auteurAjout = scanner.nextLine();
                    System.out.print("Entrez la maison d'édition du livre : ");
                    String maisonEditionAjout = scanner.nextLine();
                    System.out.print("Entrez la catégorie du livre : ");
                    String categorieAjout = scanner.nextLine();
                    Livre livreAjout = new Livre(codeAjout, nomAjout, auteurAjout, maisonEditionAjout, categorieAjout);
                    bibliotheque.ajouterLivre(livreAjout);
                    ajouterLivreDansBaseDeDonnees(livreAjout);
                    System.out.println("Le livre '" + nomAjout + " de " + auteurAjout + "' a été ajouté.");
                    break;
                case "9":
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option non valide. Veuillez réessayer.");
            }
        } while (!choix.equals("9"));

        scanner.close();
    }

    private static void chargerLivresDepuisBaseDeDonnees(Bibliotheque bibliotheque) {
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

    private static void sauvegarderLivresDansBaseDeDonnees(Bibliotheque bibliotheque) {
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

    private static void ajouterLivreDansBaseDeDonnees(Livre livre) {
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

    private static void supprimerLivreDeBaseDeDonnees(String code) {
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
}
