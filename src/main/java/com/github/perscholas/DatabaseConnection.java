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
                .setPassword("")
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
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + name().toLowerCase() + ";";
        String info;
        try {
            getScrollableStatement().execute(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        String sqlStatement = "DROP DATABASE IF EXISTS " +name().toLowerCase();
        String info;
        try {
            getScrollableStatement().execute(sqlStatement);
            console.println("drop table");
            info = "Successfully executed statement '%s'.";
        }
        catch (Exception sqlException){
            info = "Failed to execute statement '%s'.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void use() {
        String sqlStatement = "USE " + name().toLowerCase() + ";";
        String info;
        try {
            getScrollableStatement().execute(sqlStatement);
            info = "Successfully executed statement '%s'.";
        }
        catch (Exception sqlException) {
            info = "Failed to execute statement '%s'.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        String info;
        try {
            getScrollableStatement().execute(sqlStatement.trim());
            info = "Successfully executed statement '%s'.";
        }
        catch (Exception sqlException) {
            info = "Failed to execute statement '%s'.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        String info;
        ResultSet resultSet = null;
        try {
            resultSet = getScrollableStatement().executeQuery(sqlQuery);
            info = "Successfully executed statement '%s'.";
        }
        catch (Exception sqlException) {
            info = "Failed to execute statement '%s'.";
        }
        console.println(info, sqlQuery);
        return resultSet;
    }

    private Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}