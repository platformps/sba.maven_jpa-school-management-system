package com.github.perscholas.config;

import com.github.perscholas.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    MANAGEMENT_SYSTEM,
    UAT;

    private static final IOConsole console = new IOConsole(IOConsole.AnsiColor.CYAN);
    private final JpaConnectionBuilder connectionBuilder;
    private static final EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("SMS");
    
    DatabaseConnection(JpaConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    DatabaseConnection() {
        this(new JpaConnectionBuilder().setUrl("jdbc:h2:~/test").setUser("").setPassword(""));
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
    @Override
    public String getDatabaseName() {
        return "test";
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder.build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void create() {
        executeSqlFile("student_course.create-table.sql");
        executeSqlFile("courses.create-table.sql");
        executeSqlFile("courses.populate-table.sql");
        executeSqlFile("students.create-table.sql");
        executeSqlFile("students.populate-table.sql");
    }

    @Override
    public void drop() {
        //Not used
    }

    @Override
    public void use() {
        //Not used
    }

    @Override
    public void executeStatement(String sqlStatement) {
        return;
    }
    
    private static void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            if (statement == null || statement.isEmpty()) {
                continue;
            }
            //console.println(statement);
            entityManager.createNativeQuery(statement).executeUpdate();
            entityTransaction.commit();
            entityManager.close();
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        return null;
    }
}