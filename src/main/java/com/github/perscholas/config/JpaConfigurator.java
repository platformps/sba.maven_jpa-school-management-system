package com.github.perscholas.config;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;

public class JpaConfigurator {
    
    private static final DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;
    private static EntityManagerFactory entityManagerFactory;
    public static synchronized EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("SMS");
            return entityManagerFactory;
        }
        return entityManagerFactory;
    }
    
    public static void initialize() {
        getEntityManagerFactory().close();
        entityManagerFactory = null;
        getEntityManagerFactory();
        dbc.drop();
        dbc.create();
        dbc.populate();
    }
    
    public static void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            dbc.executeStatement(statement);
        }
    }
}
