package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    MANAGEMENT_SYSTEM,
    UAT;

    private static final IOConsole console = new IOConsole(IOConsole.AnsiColor.CYAN);
    private final ConnectionBuilder connectionBuilder;

    DatabaseConnection(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    DatabaseConnection() {
        this(new ConnectionBuilder()
                .setUser("root")
                .setPassword("dbpassword")
                .setPort(3306)
                .setDatabaseVendor("mysql")
                .setHost("127.0.0.1"));
    }

    @Override
    public String getDatabaseName() {
        return name().toLowerCase();
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder
                .setDatabaseName(getDatabaseName())
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void create() {
        String sqlStatement =  "CREATE DATABASE IF NOT EXISTS " + name().toLowerCase() + ";";
        String message;
        try {
            getDatabaseEngineConnection().prepareStatement(sqlStatement).execute();
            message = "Successfully executed statement";
        } catch (Exception sqlException) {
            message = "Error executing statement";
        }
        console.println(message, sqlStatement);
    }

    @Override
    public void drop() {
        String sqlStatement =  "DROP DATABASE IF EXISTS " + name().toLowerCase() + ";";
        String message;
        try {
            getDatabaseEngineConnection().prepareStatement(sqlStatement).execute();
            message = "Successfully executed statement";
        } catch (Exception sqlException) {
            message = "Error executing statement";
        }
        console.println(message, sqlStatement);
    }

    @Override
    public void use() {
        try {
            String sqlStatement = "USE " + name().toLowerCase() + ";";
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            console.println("Successfully executed statement");

        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            sqlStatement = sqlStatement.trim();
           getDatabaseConnection().createStatement().execute(sqlStatement);
            console.println("Successfully executed statement\n"+sqlStatement);
        } catch (SQLException e) {
            console.println("Error executing statement\n"+sqlStatement);
            throw new Error(e);
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            sqlQuery = sqlQuery.trim();
            ResultSet result = getDatabaseConnection().createStatement().executeQuery(sqlQuery);
            console.println("Successfully executed query\n"+sqlQuery);
            return result;
        } catch (SQLException e) {
            console.println("Failed to execute query \n"+sqlQuery);
            throw new Error(e);
        }
    }
}