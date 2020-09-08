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
                .setPassword("root")
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
        String sqlStatement = "CREATE DATABASE " + name().toLowerCase();
        String info;
        try {
            executeStatement(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        String sqlStatement = "DROP DATABASE IF EXISTS " + name().toLowerCase();
        String info;
        try {
            executeStatement(sqlStatement);
            info = "Successfully dropped statement the database `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to dropped statement the database `%s`.";
        }
        console.println(info, name().toLowerCase());
    }

    @Override
    public void use() {
        String sqlStatement = "USE " + name().toLowerCase();
        String info;
        try {
            executeStatement(sqlStatement);
            info = "Successfully using statement the database `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to used statement the database `%s`.";
        }
        console.println(info, name().toLowerCase());
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            getDatabaseEngineConnection().createStatement().execute(sqlStatement);
        } catch (SQLException e) {
            try {
                getDatabaseConnection().createStatement().execute(sqlStatement);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            return getDatabaseConnection().createStatement().executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}