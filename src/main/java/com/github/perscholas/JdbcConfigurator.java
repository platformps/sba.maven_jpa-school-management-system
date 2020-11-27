package com.github.perscholas;

import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.io.File;

public class JdbcConfigurator {
    static {
        try {
           // DriverManager.registerDriver(Driver.class.newInstance());
            Class.forName(Driver.class.getName());
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private static final DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;
    private  static final DatabaseConnection db_UAT = DatabaseConnection.UAT;

    public static void initialize() {
        dbc.drop();
        dbc.create();
        dbc.use();
        executeSqlFile("courses.create-table.sql");
        executeSqlFile("courses.populate-table.sql");
        executeSqlFile("students.create-table.sql");
        executeSqlFile("students.populate-table.sql");
        executeSqlFile("student-course.create-table.sql");
        executeSqlFile("student-course.populate-table.sql");
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
