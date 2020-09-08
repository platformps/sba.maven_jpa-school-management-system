package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.ResultSet;
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

    DatabaseConnection()
    {
        this(new ConnectionBuilder()
                .setUser("root")
                .setPassword("maria")
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
        Statement sqlStatement = null; // TODO - define statement
        String info="CREATE DATABASE "+DatabaseConnection.MANAGEMENT_SYSTEM;
        Connection con = getDatabaseEngineConnection();

        try {
            sqlStatement = con.createStatement();
            sqlStatement.executeQuery(info);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public void drop() {
        String s=getDatabaseName();
        System.out.println("GetDatabaseNAme"+s);
        String dropSQL="DROP DATABASE IF EXISTS "+s;
        executeStatement(dropSQL);
    }

    @Override
    public void use() {
        String s=getDatabaseName();
        String useSQL="USE "+s;
        executeStatement(useSQL);
    }

    @Override
    public void executeStatement(String sqlStatement) {
        //return null;
        Statement statement = null;
        Connection con  = null;
        ResultSet resultSet = null;

        String info;
        try {
            // TODO - execute statement
            con  = getDatabaseConnection();
            System.out.println("Execute executeStatement 0  "+sqlStatement);
            statement = con.createStatement();
            resultSet= statement.executeQuery(sqlStatement);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        //return null;
        String sqlStatement = null; // TODO - define statement
        Statement statement = null;
        Connection con  = null;
        ResultSet resultSet = null;

        String info;
        try {
            // TODO - execute statement
            con  = getDatabaseConnection();
            use();
            System.out.println("Execute Query 0 sqlQuery "+sqlQuery);
            statement = con.createStatement();
            resultSet= statement.executeQuery(sqlQuery);
            info = "Successfully executed statement `%s`.";
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            info = "Failed to executed statement `%s`.";
        }
        console.println(info, sqlStatement);
        return resultSet;
    }
}