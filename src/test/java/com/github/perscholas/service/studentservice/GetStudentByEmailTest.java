package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */

/**
 * TODO implemented by Monica Deshmukh
 * 9/7/2020
 */
public class GetStudentByEmailTest {
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
        DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;

        // when
        // TODO - define `when` clause
        List<StudentInterface> studentList = service.getAllStudents();
        String email = studentList.get(0).getEmail();

        // then
        // TODO - define `then` clause
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student WHERE email = '" + email +"'");
        try {
            // TODO - Parse `List<StudentInterface>` from `resultSet`
            List<StudentInterface> studentInDatabase = new ArrayList<>();
            //supposed to return one record
            while (resultSet.next()) {
                studentInDatabase.add(new Student(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("password")));
            }
            Assert.assertTrue(studentList.get(0).equals(studentInDatabase.get(0)));
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
