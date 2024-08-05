package com.example.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Bibliotheque {
    private HashMap<String, Livre> livres;
    public HashMap<String, Livre> getLivres;

    public Bibliotheque() {
        this.livres = new HashMap<>();
    }

    // Ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        livres.put(livre.getCode(), livre);
    }

    // Obtenir un livre par son code
    public Livre getLivre(String code) {
        return livres.get(code);
    }

    // Supprimer un livre par son code
    public void supprimerLivre(String code) {
        livres.remove(code);
    }

    // Modifier un livre
    public void modifierLivre(String code, Livre nouveauLivre) {
        livres.put(code, nouveauLivre);
    }

    // Rechercher un livre par nom
    public Livre rechercherParNom(String nom) {
        return livres.values().stream()
                .filter(livre -> livre.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

    // Lister les livres en saisissant une lettre alphabétique
    public Map<Object, Object> listerLivresParLettre(char lettre) {
        return livres.entrySet().stream()
                .filter(entry -> entry.getValue().getNom().charAt(0) == lettre)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    // Afficher le nombre de livres
    public int nombreDeLivres() {
        return livres.size();
    }

    // Afficher les livres par catégorie
    public Map<Object, Object> livresParCategorie(String categorie) {
        return livres.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Roman
                        && ((Roman) entry.getValue()).getCategorie().equals(categorie))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public String getNombreTotalLivres() {
        throw new UnsupportedOperationException("Unimplemented method 'getNombreTotalLivres'");
    }

    public Livre[] getLivres() {
        throw new UnsupportedOperationException("Unimplemented method 'getLivres'");
    }
}
