package com.github.perscholas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnectionInterface {
    String getDatabaseName();
    Connection getDatabaseConnection();
    Connection getDatabaseEngineConnection();
    void drop();
    void create();
    void use();
    //GN changed return type to account for error code
    int executeStatement(String sqlStatement) throws SQLException;
    ResultSet executeQuery(String sqlQuery);
}
