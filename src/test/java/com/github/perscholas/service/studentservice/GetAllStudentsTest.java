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
    @Before
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
    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao service = (StudentDao) new StudentService();
        DatabaseConnection dbc = DatabaseConnection.UAT;

        // when
        List<StudentInterface> studentList = service.getAllStudents();

        // then
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            List<StudentInterface> studentsInDatabase = new ArrayList<>();
            while (resultSet.next()) {
                studentsInDatabase.add(new Student(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            Assert.assertArrayEquals(studentList.toArray(), studentsInDatabase.toArray());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
