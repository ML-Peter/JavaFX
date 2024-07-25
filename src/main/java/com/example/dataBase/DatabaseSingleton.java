package com.example.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private Connection connection;

    private DatabaseSingleton() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/livre", "root", "root");
            System.out.println("La connexion à la base de données a été établie avec succès.");
        } catch (ClassNotFoundException ex) {
            System.out.println("La création de la connexion à la base de données a échoué : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseSingleton();
        }

        return instance;
    }
}