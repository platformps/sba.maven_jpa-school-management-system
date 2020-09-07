package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
    private final DatabaseConnection dbc=DatabaseConnection.MANAGEMENT_SYSTEM;
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
        DirectoryReference directoryReference = DirectoryReference.RESOURCE_DIRECTORY;
        File coursesSchemaFile = directoryReference.getFileFromDirectory("courses.create-table.sql");
        File studentsSchemaFile = directoryReference.getFileFromDirectory("students.create-table.sql");
        File coursesPopulatorFile = directoryReference.getFileFromDirectory("courses.populate-table.sql");
        File studentsPopulatorFile = directoryReference.getFileFromDirectory("students.populate-table.sql");
        File[] filesToExecute = new File[]{
                coursesSchemaFile,
                studentsSchemaFile,
                coursesPopulatorFile,
                studentsPopulatorFile
        };
    }

    // given
    // TODO - Add `@Test` annotation
    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao service = (StudentDao) new StudentService();
        String email="sbowden1@yellowbook.com";

        // when
        // TODO - define `when` clause
        String expected= service.getStudentByEmail(email).getEmail();
        ResultSet resultSet = dbc.executeQuery("SELECT email FROM Student where email='"+email+"'");
        String actual = null;
        try {
            resultSet.next();
            actual= resultSet.getString("email");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        // then
        // TODO - define `then` clause
        Assert.assertTrue(expected.equals(actual));
    }
}
