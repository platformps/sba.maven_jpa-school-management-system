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
    private String name = null;

    private static final IOConsole console = new IOConsole(IOConsole.AnsiColor.CYAN);
    private final ConnectionBuilder connectionBuilder;

    DatabaseConnection(ConnectionBuilder connectionBuilder) {

        this.connectionBuilder = connectionBuilder;
    }

    DatabaseConnection() {
        this(new ConnectionBuilder()
                .setUser("admin")
                .setPassword("password")
                .setPort(3306)
                .setDatabaseVendor("mariadb")
                .setHost("127.0.0.1"));

    }

    @Override
    public String getDatabaseName() {
        return name;
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder
                .setDatabaseName(getDatabaseName())
                .build();
    }

    public Statement getScrollableStatement(Connection connection) {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void create() {
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + this.name().toLowerCase()+ ";";
        String info;
        try {
            executeStatement (sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
        this.name = this.name().toLowerCase();
    }

    @Override
    public void drop() {
        String sqlStatement = "DROP DATABASE IF EXISTS " + this.name().toLowerCase() + ";";
        String info;
        try {
            executeStatement (sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);

    }

    @Override
    public void use() {
        String sqlStatement = "USE " + name+ ";";
        String info;
        try {
            executeStatement (sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }


    @Override
    public void executeStatement(String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(getDatabaseConnection());
            statement.execute(sqlStatement);
            statement.close();
        } catch (SQLException e) {
            throw new Error(e);
        }

    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            Statement statement = getScrollableStatement(getDatabaseConnection());
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public void setName(String newName) {
        name = newName;
    }
}