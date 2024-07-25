package com.example.classes;
import java.sql.*;
import java.util.Scanner;

import com.example.dataBase.DatabaseOperations;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Bibliotheque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Créer la table 'Livre' si elle n'existe pas
        DatabaseOperations dbOps = new DatabaseOperations();
        dbOps.createTableForClass(Livre.class);

        // Charger les livres depuis la base de données
        DatabaseOperations.chargerLivresDepuisBaseDeDonnees(bibliotheque);

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
                    DatabaseOperations.sauvegarderLivresDansBaseDeDonnees(bibliotheque);
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
                    DatabaseOperations.supprimerLivreDeBaseDeDonnees(codeSuppr);
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
                    DatabaseOperations.ajouterLivreDansBaseDeDonnees(livreAjout);
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
}
