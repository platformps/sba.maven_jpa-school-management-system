package com.github.perscholas;

import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;

import java.io.File;
import java.sql.Driver;

public class JdbcConfigurator {
    private static DatabaseConnectionInterface dbc;

    static {
        try {
            Class.forName(Driver.class.getName());
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    public static void initialize(){
        initialize(DatabaseConnection.MANAGEMENT_SYSTEM);
    }


    public static void initialize(DatabaseConnectionInterface dbConnection) {
        dbc = dbConnection;
        dbc.drop();
        dbc.create();
        dbc.use();
        executeSqlFile("courses.create-table.sql");
        executeSqlFile("courses.populate-table.sql");
        executeSqlFile("students.create-table.sql");
        executeSqlFile("students.populate-table.sql");
        executeSqlFile("student-course.create-table.sql");
        executeSqlFile("student-courses.populate-table.sql");

    }

    private static void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            dbc.executeStatement(statement);
        }
    }
}
