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
                .setPassword("password")
                .setPort(3306)
                .setDatabaseVendor("mysql")
                .setHost("127.0.0.1"));
    }
    @Override
    public String getDatabaseName()
    {
        return name().toLowerCase();
    }
    @Override
    public Connection getDatabaseConnection()
    {
        return connectionBuilder
                .setDatabaseName(getDatabaseName())
                .build();
    }
    @Override
    public Connection getDatabaseEngineConnection()
    {
        return connectionBuilder.build();
    }
    @Override
    public void create() {
        //creating Database
        String sqlStatement = "CREATE DATABASE " + name().toLowerCase();
        String info;
        try {
            //Database Created
            executeStatement(sqlStatement);
            info = "Successfully executed CREATE statement `%s`.";
        }
        catch (Exception sqlException) {
            info = "Failed to execute CREATE statement `%s`.";
        }
        console.println(info, sqlStatement);
    }
    @Override
    public void drop() {
        //Dropping Database if already exists
        //System.out.println("Database Name : " + name().toLowerCase());
        String sqlStatement = "DROP DATABASE IF EXISTS " +name().toLowerCase();
        String info;
        try {
            executeStatement(sqlStatement);
            info = "Successfully executed DROP statement '%s'.";
        }
        catch (Exception sqlException){
            info = "Failed to execute DROP statement '%s'.";
        }
        console.println(info, name().toLowerCase());
        //console.println(info, sqlStatement);
    }

    @Override
    public void use() {
        //Use Main Schema
        String sqlStatement = "ALTER SESSION SET CURRENT_SCHEMA= " + name().toLowerCase();
        //String sqlStatement = "USE " + name().toLowerCase();
        String info;
        try {
            executeStatement(sqlStatement);
            info = "Successfully executed USE statement '%s'.";
        }
        catch (Exception sqlException) {
            info = "Failed to execute USE statement '%s'.";
        }
        console.println(info, name().toLowerCase());
        //console.println(info, sqlStatement);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        //Executing SQL Statement
        try {
            console.println( sqlStatement);
            getDatabaseEngineConnection().createStatement().execute(sqlStatement);
        }
        catch (SQLException se) {
            System.out.println("SQL exception is thrown");;
        }
    }
    @Override
    public ResultSet executeQuery(String sqlQuery) {
        //Executing SQL Query
        try {
            return getDatabaseConnection().createStatement().executeQuery(sqlQuery);
        }
        catch (SQLException se){
            throw new RuntimeException(se);
        }
    }
}