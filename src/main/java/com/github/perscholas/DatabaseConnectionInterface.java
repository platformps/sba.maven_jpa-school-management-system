package com.github.perscholas;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DatabaseConnectionInterface {
    String getDatabaseName();
    Connection getDatabaseConnection(); //connect to specific database
    Connection getDatabaseEngineConnection(); //connect to mysql
    void drop();
    void create(); //uses jpa entity manager to create new entities in database
    void use();
    void executeStatement(String sqlStatement);
    ResultSet executeQuery(String sqlQuery);
}
