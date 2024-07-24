package com.example;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
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
}