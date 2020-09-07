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
        JpaConfigurator.executeSqlFile("student_course.create-table.sql");
        JpaConfigurator.executeSqlFile("courses.create-table.sql");
        JpaConfigurator.executeSqlFile("courses.populate-table.sql");
        JpaConfigurator.executeSqlFile("students.create-table.sql");
        JpaConfigurator.executeSqlFile("students.populate-table.sql");    }

    @Override
    public void drop() {
        JpaConfigurator.executeSqlFile("student_course.drop-table.sql");
        JpaConfigurator.executeSqlFile("courses.drop-table.sql");
        JpaConfigurator.executeSqlFile("students.drop-table.sql");
    }

    @Override
    public void use() {
        //Not used
    }

    @Override
    public void executeStatement(String sqlStatement) {
        if (sqlStatement == null || sqlStatement.isEmpty()) {
            return;
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createNativeQuery(sqlStatement).executeUpdate();
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        return null;
    }
}