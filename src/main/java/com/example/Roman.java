package com.example;

public class Roman extends Livre {
    private String categorie;

    public Roman(String code, String nom, String auteur, String maisonEdition, String categorie) {
        super(code, nom, auteur, maisonEdition, categorie);
        this.categorie = categorie;
    }

    // Getter et Setter pour categorie
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
