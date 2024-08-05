package com.example.controller;
import com.example.dataBase.DatabaseOperations;
import com.example.classes.Bibliotheque;
import com.example.classes.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtAuteur;
    @FXML
    private TextField txtMaisonEdition;
    @FXML
    private TextField txtCategorie;
    @FXML
    private ListView<Livre> listView;

    private Bibliotheque bibliotheque;
    private ObservableList<Livre> livresList;

    @FXML
    private void initialize() {
        bibliotheque = new Bibliotheque();
        livresList = FXCollections.observableArrayList();
        listView.setItems(livresList);

        // Charger les livres depuis la base de données
        DatabaseOperations.chargerLivresDepuisBaseDeDonnees(bibliotheque);
        livresList.addAll(bibliotheque.getLivres());
    }

    @FXML
    private void handleAddBook() {
        String code = txtCode.getText();
        String nom = txtNom.getText();
        String auteur = txtAuteur.getText();
        String maisonEdition = txtMaisonEdition.getText();
        String categorie = txtCategorie.getText();

        Livre livre = new Livre(code, nom, auteur, maisonEdition, categorie);
        livresList.add(livre);

        // Ajouter le livre à la base de données
        DatabaseOperations.ajouterLivre(livre);
    }

    @FXML
    private void handleEditBook() {
        Livre selectedLivre = listView.getSelectionModel().getSelectedItem();
        if (selectedLivre != null) {
            selectedLivre.setCode(txtCode.getText());
            selectedLivre.setNom(txtNom.getText());
            selectedLivre.setAuteur(txtAuteur.getText());
            selectedLivre.setMaisonEdition(txtMaisonEdition.getText());
            selectedLivre.setCategorie(txtCategorie.getText());

            // Mettre à jour le livre dans la base de données
            DatabaseOperations.mettreAJourLivre(selectedLivre);
        }
    }

    @FXML
    private void handleDeleteBook() {
        Livre selectedLivre = listView.getSelectionModel().getSelectedItem();
        if (selectedLivre != null) {
            livresList.remove(selectedLivre);

            // Supprimer le livre de la base de données
            DatabaseOperations.supprimerLivre(selectedLivre);
        }
    }
}
