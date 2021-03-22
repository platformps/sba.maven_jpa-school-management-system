package com.github.perscholas;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DatabaseConnectionInterface {

    String getDatabaseName();

    String getParameters();

    //public interface
    Connection getDatabaseConnection();
    //public interface
    Connection getDatabaseEngineConnection();
    //methods
    void drop();
    void create();
    void use();
    void executeStatement(String sqlStatement);
    //public interface
    ResultSet executeQuery(String sqlQuery);
}
