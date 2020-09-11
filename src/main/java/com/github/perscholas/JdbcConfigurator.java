package com.github.perscholas;

import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;
import com.mysql.cj.jdbc.Driver;

import java.io.File;
import java.sql.DriverManager;


public class JdbcConfigurator {
    static {
        try {
            // register JDBC Driver
            DriverManager.registerDriver(Driver.class.newInstance());
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    //create instance of DatabaseConnection
    private static final DatabaseConnection dbc = DatabaseConnection.UAT;

    public static void initialize() {
        dbc.drop();
        dbc.create();
        dbc.use();
        executeSqlFile("courses.create-table.sql");
        executeSqlFile("courses.populate-table.sql");
        executeSqlFile("students.create-table.sql");
        executeSqlFile("students.populate-table.sql");
        executeSqlFile("studentcourses.create-table.sql");
    }

    private static void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (String statement : statements) {
            dbc.executeStatement(statement);
        }
    }
}
