package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.*;

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
                .setPassword("")
                .setPort(3306)
                .setDatabaseVendor("mariadb")
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
        String sql = "CREATE DATABASE IF NOT EXISTS " + getDatabaseName() + ";"; // TODO - define statement
        doExecution(sql);
    }

    @Override
    public void drop() {
        String sql = "DROP DATABASE IF EXISTS " + getDatabaseName() + ";";
        doExecution(sql);
    }

    @Override
    public void use() {
        String sql = "USE " + getDatabaseName() + ";";
        doExecution(sql);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            Statement statement = getDatabaseConnection().createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        ResultSet rs;
        try {
            Statement statement = getDatabaseConnection().createStatement();
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new Error(e);
        }
        return rs;
    }

    private void doExecution(String statement) {
        String info;
        try {
            executeStatement(statement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, statement);
    }
}