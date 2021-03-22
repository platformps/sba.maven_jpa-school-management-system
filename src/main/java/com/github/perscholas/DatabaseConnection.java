package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.*;
import java.lang.StringBuilder;

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
                .setPassword("Nikolaev@71")
                .setPort(3307)
                .setDatabaseVendor("mysql")
                .setHost("127.0.0.1"));
    }

    @Override
    public String getDatabaseName() {
        return name().toLowerCase();
    }

    @Override
    public String getParameters() {
        return "?serverTimezone=UTC&useLegacyDatetimeCode=false";
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder
                .setDatabaseName(getDatabaseName())
                .setParameters(getParameters())
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {

        return connectionBuilder.build();
    }

    @Override
    public void create() {
        //String sqlStatement - create and define statement
        String sqlStatement = new StringBuilder()
                .append("CREATE DATABASE IF NOT EXISTS ")
                .append(getDatabaseName())
                .append(";")
                .toString();
        String info;
        // execute statement
        try {
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        //String sqlStatement - define statement
        String sqlStatement = new StringBuilder()
                .append("DROP DATABASE IF EXISTS ")
                .append(getDatabaseName())
                .append(";")
                .toString();
        String info;
        try {
            //execute statement
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to execute statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void use() {
        //define statement
        String sqlStatement = new StringBuilder()
                .append("USE ")
                .append(getDatabaseName())
                .append(";")
                .toString();
        String info;
        try {
            //execute statement
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to execute statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        sqlStatement = sqlStatement.trim();
        String info;
        try {
            getScrollableStatement().execute(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to execute statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    //TO DO - create method
    public Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public ResultSet executeQuery (String sqlQuery){
        String info;
        try {
            sqlQuery = sqlQuery.trim();
            ResultSet resultSet = getScrollableStatement().executeQuery(sqlQuery);
            info = "Successfully executed query '%s'.";
            console.println(info, sqlQuery);
            return resultSet;
        } catch (SQLException e) {
            info = String.format("Failed to execute query '%s'.", sqlQuery);
            throw new Error(info, e);
        }
    }
}
