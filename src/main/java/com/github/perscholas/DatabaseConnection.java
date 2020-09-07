package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    MANAGEMENT_SYSTEM, UAT;

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
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + name().toLowerCase() + ";"; // TODO - define statement d
        String info;
        try {

            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();// TODO - execute statement d
            info = "Successfully executed statement `%s`.";

        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";

        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        String sqlStatement = "DROP DATABASE IF EXISTS " + name().toLowerCase() + ";";
        String info;
        try {

            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            info = "Successfully executed statement `%s`.";
        }catch (SQLException e){
            info = "Failed to execute statement `%s`";
            throw new Error(e);
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void use() {
        String sqlStatement = "USE " + name().toLowerCase() + ";";
        String info;
    try{
        getDatabaseEngineConnection()
                .prepareStatement(sqlStatement)
                .execute();
        info = "Successfully executed statement `%s`.";
    }catch (SQLException e){
        info = "Failed to execute statement `%s`";
        throw new Error (e);
    }
        console.println(info, sqlStatement);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            sqlStatement = sqlStatement.trim();
            getScrollableStatement().execute(sqlStatement);
            String info = "Successfully executed statement `%s`";
        } catch (SQLException e) {
            String info =  "Failed to execute statement `%s`";
            throw new Error(info, e);
        }

    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            sqlQuery = sqlQuery.trim();
            ResultSet result = getScrollableStatement().executeQuery(sqlQuery);
            String info = "Successfully executed query `%s`";
            console.println(info);
            return result;
        }catch (SQLException e){
            String info ="Failed to execute query`%s`";
            throw new Error(info, e);
        }
    }


    private Statement getScrollableStatement() {
        int resultSetTye = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetTye, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}