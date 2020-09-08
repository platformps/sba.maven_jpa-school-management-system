package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                .setDatabaseName(this.getDatabaseName())
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder
                .build();
    }

    @Override
    public void create() {
        String sqlStatement ="CREATE DATABASE " + this.getDatabaseName() + ";"; // TODO - define statement
        String info;
        try {
            // TODO - execute statement
            executeStatement(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        String sqlStatement ="DROP DATABASE IF EXISTS " + this.getDatabaseName() + ";";
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
    public void use() {
        String sqlStatement ="USE " + this.getDatabaseName() + ";";
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
    public void executeStatement(String sqlStatement) {
        Connection conn = this.getDatabaseEngineConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            return getDatabaseConnection().createStatement().executeQuery(sqlQuery);
        }
        catch (SQLException se){
            throw new RuntimeException(se);
        }
    }
}