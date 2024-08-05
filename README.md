# Gestion de Bibliothèque

Ce projet est une application de gestion de bibliothèque développée en Java en utilisant le framework JavaFX pour l'interface utilisateur. L'application permet d'ajouter, de modifier, de supprimer et de lister des livres, ainsi que de gérer leurs informations.

## Table des Matières

- [Introduction](#introduction)
- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Structure du Projet](#structure-du-projet)
- [Contribution](#contribution)
- [Licence](#licence)
- [Remerciements](#remerciements)

## Introduction

L'application de gestion de bibliothèque offre une interface simple et intuitive pour gérer les informations des livres. Elle est idéale pour les petites bibliothèques ou les particuliers souhaitant organiser leurs collections de livres.

## Fonctionnalités

- Ajouter de nouveaux livres.
- Modifier les informations des livres existants.
- Supprimer des livres.
- Afficher la liste des livres.
- Voir les détails de chaque livre.

## Technologies Utilisées

- Java 21
- JavaFX 22
- IDE: Visual Studio Code
- Base de données: [Nom de la base de données, par exemple SQLite]

## Prérequis

- Java 21 installé ([Télécharger Java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html))
- JavaFX SDK 22 ([Télécharger JavaFX](https://gluonhq.com/products/javafx/))
- Visual Studio Code ([Télécharger VS Code](https://code.visualstudio.com/))

## Installation

1. Clonez le dépôt :

    ```bash
    git clone https://github.com/ML-Peter/JavaFX.git
    cd votre-repo
    ```

2. Assurez-vous que Java et JavaFX sont correctement installés et configurés.

3. Configurez les variables d'environnement pour JavaFX.

4. Ouvrez le projet dans Visual Studio Code.

5. Compilez et exécutez le projet :

    ```bash
    /path/to/java --module-path /path/to/javafx-sdk-22/lib --add-modules javafx.controls -cp out/production/classes com.example.classes.Main
    ```

## Utilisation

1. Lancez l'application.
2. Utilisez l'interface pour ajouter, modifier, supprimer et consulter les livres.
3. Consultez les messages et les détails des livres dans les onglets dédiés.

## Structure du Projet

- `src/com/example/classes` : Contient les classes principales du projet.
- `src/com/example/controllers` : Contient les contrôleurs JavaFX.
- `src/com/example/database` : Contient les classes de gestion de la base de données.
- `src/com/example/views` : Contient les fichiers FXML pour l'interface utilisateur.
- `lib` : Dossier contenant les librairies externes nécessaires (comme JavaFX).

## Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Fork le dépôt.
2. Créez une branche pour votre fonctionnalité (`git checkout -b ma-nouvelle-fonctionnalité`).
3. Commitez vos modifications (`git commit -m 'Ajouter une nouvelle fonctionnalité'`).
4. Poussez votre branche (`git push origin ma-nouvelle-fonctionnalité`).
5. Ouvrez une Pull Request.

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Remerciements

Merci à tous ceux qui ont contribué à ce projet.

---

Si vous avez des questions ou des suggestions, n'hésitez pas à ouvrir une issue ou à me contacter directement.
