package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.sql.ResultSet;

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
                .setPort(8889)
                .setDatabaseVendor("mysql")
                .setHost("localhost"));
    }

    @Override
    public String getDatabaseName() {
        return name().toLowerCase();
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder
                .setDatabaseName("")
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void create() {
        String sqlStatement ="DROP DATABASE IF EXISTS management_system; CREATE DATABASE IF NOT EXISTS management_system; USE management_system;"; // TODO - define statement
        String info;
        try {
            // TODO - execute statement
            getDatabaseConnection();
            executeStatement(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
    }

    @Override
    public void use() {
    }

    @Override
    public void executeStatement(String sqlStatement) {

    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        return null;
    }
}