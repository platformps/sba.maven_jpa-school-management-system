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
 * @created 02/12/2020 - 8:22 PM
 */

/**
 * TODO implemented by Monica Deshmukh
 * 9/7/2020
 */
public class GetAllStudentsTest {
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
        List<StudentInterface> studentList = service.getAllStudents();

        // then
        // TODO - define _then_ clause
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            // TODO - Parse `List<StudentInterface>` from `resultSet`
            List<StudentInterface> studentsInDatabase = new ArrayList<>();
            while (resultSet.next()) {
                studentsInDatabase.add(new Student(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("password")));
            }
            //since we are comparing two Lists, we use assertArrayEquals, so that it compares each element in the two lists
            Assert.assertArrayEquals(studentList.toArray(), studentsInDatabase.toArray());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
