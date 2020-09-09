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
        List<StudentInterface> actualStudentList = service.getAllStudents();

        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        List<StudentInterface> expectedStudentList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student eStudent = new Student();
                eStudent.setEmail(resultSet.getString("email").toString());
                eStudent.setName(resultSet.getString("name").toString());
                eStudent.setPassword(resultSet.getString("password").toString());
                expectedStudentList.add(eStudent);
            }
            //return courseInterfaceList;
        } catch (Exception e) {
            throw new Error(e);
        }

        // then
        // TODO - define `then` clause
        Assert.assertArrayEquals(expectedStudentList.toArray(), actualStudentList.toArray());
    }
}
