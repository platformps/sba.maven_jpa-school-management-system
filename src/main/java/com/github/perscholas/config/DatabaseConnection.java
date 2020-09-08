package com.github.perscholas.config;

import com.github.perscholas.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    MANAGEMENT_SYSTEM,
    UAT;
    
    private static final IOConsole console = new IOConsole(IOConsole.AnsiColor.CYAN);
    private final JpaConnectionBuilder connectionBuilder;
    
    DatabaseConnection(JpaConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }
    
    DatabaseConnection() {
        this(new JpaConnectionBuilder().setUrl("jdbc:h2:~/test").setUser("").setPassword(""));
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
        JpaConfigurator.executeSqlFile("courses.create-table.sql");
        JpaConfigurator.executeSqlFile("students.create-table.sql");
        JpaConfigurator.executeSqlFile("student_course.create-table.sql");
    }
    
    @Override
    public void drop() {
        JpaConfigurator.executeSqlFile("courses.drop-table.sql");
        JpaConfigurator.executeSqlFile("student_course.drop-table.sql");
        JpaConfigurator.executeSqlFile("students.drop-table.sql");
    }
    
    @Override
    public void populate() {
        JpaConfigurator.executeSqlFile("courses.populate-table.sql");
        JpaConfigurator.executeSqlFile("students.populate-table.sql");
    }
    
    @Override
    public void executeStatement(String sqlStatement) {
        if (sqlStatement == null || sqlStatement.isEmpty()) {
            return;
        }
        EntityManager entityManager = JpaConfigurator.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        // console.println(sqlStatement);
        entityManager.createNativeQuery(sqlStatement).executeUpdate();
        entityTransaction.commit();
        entityManager.close();
    }
    
    @Override
    public ResultSet executeQuery(String sqlQuery) {
        throw new UnsupportedOperationException("Use correct respository if you need to perform queries.");
    }
}