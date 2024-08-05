package com.example.classes;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.dataBase.DatabaseOperations;
import com.example.classes.Livre;
import com.example.classes.Bibliotheque;

public class Main extends Application {

    private Bibliotheque bibliotheque;
    private ObservableList<Livre> livresList;

    @Override
    public void start(Stage primaryStage) {
        // Créer une instance de Bibliotheque
        bibliotheque = new Bibliotheque();

        // Créer la table 'Livre' si elle n'existe pas
        DatabaseOperations dbOps = new DatabaseOperations();
        dbOps.createTableForClass(Livre.class);

        // Charger les livres depuis la base de données
        DatabaseOperations.chargerLivresDepuisBaseDeDonnees(bibliotheque);

        // Créer l'interface utilisateur
        createUI(primaryStage);
    }

    private void createUI(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Création des éléments de l'interface
        Label lblCode = new Label("Code :");
        TextField txtCode = new TextField();
        Label lblNom = new Label("Nom :");
        TextField txtNom = new TextField();
        Label lblAuteur = new Label("Auteur :");
        TextField txtAuteur = new TextField();
        Label lblMaisonEdition = new Label("Maison d'édition :");
        TextField txtMaisonEdition = new TextField();
        Label lblCategorie = new Label("Catégorie :");
        TextField txtCategorie = new TextField();
        Button btnAjouter = new Button("Ajouter");
        Button btnModifier = new Button("Modifier");
        Button btnSupprimer = new Button("Supprimer");
        ListView<Livre> lvLivres = new ListView<>();
        livresList = FXCollections.observableArrayList(bibliotheque.getLivres());
        lvLivres.setItems(livresList);

        // Placement des éléments dans la grille
        grid.add(lblCode, 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(lblNom, 0, 1);
        grid.add(txtNom, 1, 1);
        grid.add(lblAuteur, 0, 2);
        grid.add(txtAuteur, 1, 2);
        grid.add(lblMaisonEdition, 0, 3);
        grid.add(txtMaisonEdition, 1, 3);
        grid.add(lblCategorie, 0, 4);
        grid.add(txtCategorie, 1, 4);
        grid.add(btnAjouter, 0, 5);
        grid.add(btnModifier, 1, 5);
        grid.add(btnSupprimer, 2, 5);
        grid.add(lvLivres, 2, 0, 1, 5);

        // Gestion des événements
        btnAjouter.setOnAction(event -> ajouterLivre(txtCode.getText(), txtNom.getText(), txtAuteur.getText(),
                txtMaisonEdition.getText(), txtCategorie.getText()));
        btnModifier.setOnAction(event -> modifierLivre(lvLivres.getSelectionModel().getSelectedItem()));
        btnSupprimer.setOnAction(event -> supprimerLivre(lvLivres.getSelectionModel().getSelectedItem()));
        lvLivres.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> afficherLivre(newSelection));

        // Création de la scène et affichage de la fenêtre
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bibliothèque");
        primaryStage.show();
    }

    private void ajouterLivre(String code, String nom, String auteur, String maisonEdition, String categorie) {
        Livre livre = new Livre(code, nom, auteur, maisonEdition, categorie);
        bibliotheque.ajouterLivre(livre);
        livresList.add(livre);
    }

    private void modifierLivre(Livre livre) {
        // Récupérer les nouvelles valeurs des champs
        String code = livre.getCode();
        String nom = livre.getNom();
        String auteur = livre.getAuteur();
        String maisonEdition = livre.getMaisonEdition();
        String categorie = livre.getCategorie();

        // Créer un nouveau livre avec les nouvelles valeurs
        Livre nouveauLivre = new Livre(code, nom, auteur, maisonEdition, categorie);
        bibliotheque.modifierLivre(code, nouveauLivre);

        // Mettre à jour la liste des livres
        livresList.set(livresList.indexOf(livre), nouveauLivre);
    }

    private void supprimerLivre(Livre livre) {
        bibliotheque.supprimerLivre(livre.getCode());
        livresList.remove(livre);
    }

    private void afficherLivre(Livre livre) {
        Label txtCode = new Label();
        Label txtNom = new Label();
        Label txtMaisonEdition = new Label();
        Label txtAuteur = new Label();
        Label txtCategorie = new Label();

        if (livre != null) {
            // Remplir les champs avec les informations du livre sélectionné
            txtCode.setText(livre.getCode());
            txtNom.setText(livre.getNom());
            txtAuteur.setText(livre.getAuteur());
            txtMaisonEdition.setText(livre.getMaisonEdition());
            txtCategorie.setText(livre.getCategorie());
        } else {
            // Vider les champs
            txtCode.setText("");
            txtNom.setText("");
            txtAuteur.setText("");
            txtMaisonEdition.setText("");
            txtCategorie.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
