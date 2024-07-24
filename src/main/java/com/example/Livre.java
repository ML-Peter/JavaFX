package com.example;

public class Livre {
    private String code; // ID du livre
    private String nom; // Nom du livre
    private String auteur; // Nom de l'auteur
    private String maisonEdition; // Maison d'édition
    private String categorie; // Cat

    // Constructeur
    public Livre(String code, String nom, String auteur, String maisonEdition, String categorie) {
        this.code = code;
        this.nom = nom;
        this.auteur = auteur;
        this.maisonEdition = maisonEdition;
        this.categorie = categorie;
    }

    // Getters et Setters

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition(String maisonEdition) {
        this.maisonEdition = maisonEdition;
    }

    public Livre[] values() {
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }
}
