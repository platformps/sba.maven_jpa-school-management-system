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

/**
 * TODO implemented by Monica Deshmukh
 * 9/6/2020
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    /*MANAGEMENT_SYSTEM(new ConnectionBuilder()
            .setUser("root")
            .setPassword("5000")
            .setPort(3300)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1")),
    UAT(new ConnectionBuilder()
            .setUser("root")
            .setPassword("5000")
            .setPort(3300)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1"));*/
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
       /*this(new ConnectionBuilder()
                .setUser("root")
                .setPassword("5000")
                .setPort(3300)
                .setDatabaseVendor("mariadb")
                .setHost("127.0.0.1"));*/
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
        //String sqlStatement = null; // TODO - define statement
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + getDatabaseName() + ";" ;
        String info;
        try {
            // TODO - execute statement
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
    public void drop() {
        String sqlStatement = "DROP DATABASE IF EXISTS " + getDatabaseName() + ";" ;
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
        String sqlStatement = "USE " + getDatabaseName() + ";" ;
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
        sqlStatement = sqlStatement.trim(); //remove leading and trailing spaces
        String info;
        try {
            getScrollableStatement().execute(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to execute statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        String info;
        try{
            sqlQuery = sqlQuery.trim();
            ResultSet resultSet = getScrollableStatement().executeQuery(sqlQuery);
            info = "Successfully executed query '%s'.";
            console.println(info, sqlQuery);
            return resultSet;
        }catch (SQLException e){
            info = String.format("Failed to execute query '%s'.", sqlQuery);
            throw new Error(info, e);
        }
    }

    public Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}