package com.github.perscholas.config;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DatabaseConnectionInterface {
    String getDatabaseName();
    
    Connection getDatabaseConnection();
    
    Connection getDatabaseEngineConnection();
    
    void drop();
    
    void create();
    
    void populate();
    
    void executeStatement(String sqlStatement);
    
    ResultSet executeQuery(String sqlQuery);
}
