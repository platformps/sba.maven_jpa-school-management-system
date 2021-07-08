package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
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
 * @created 02/12/2020 - 8:25 PM
 */
/**
 * TODO implemented by Monica Deshmukh
 * 9/7/2020
 */
public class GetStudentCoursesTest {
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
        List<CourseInterface> registeredCourses = service.getStudentCourses(email);

        // then
        // TODO - define `then` clause
        try{
            List<CourseInterface> expectedCourses = new ArrayList<>();
            String statement = "SELECT c.id, c.name, c.instructor " +
                    "FROM Course c, StudentCourses sc " +
                    "WHERE sc.studentEmail = '" + email +"' " +
                    "AND sc.courseId = c.id";
            ResultSet resultSet = dbc.executeQuery(statement);
            while (resultSet.next()) {
                expectedCourses.add(new Course(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.instructor")));
            }
            Assert.assertArrayEquals(registeredCourses.toArray(),expectedCourses.toArray());
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
