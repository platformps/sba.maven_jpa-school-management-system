package com.github.perscholas.service.courseservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
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
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
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
    private void test() {
        JdbcConfigurator.initialize();
        CourseService service = (CourseService) new CourseService();
        DatabaseConnection dbc = DatabaseConnection.UAT;

        // when
        List<CourseInterface> courseList = service.getAllCourses();

        // then
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Course");
        try {
            List<CourseInterface> courseInDatabase = new ArrayList<>();
            while (resultSet.next()) {
                courseInDatabase.add(new Course(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            Assert.assertArrayEquals(courseList.toArray(), courseInDatabase.toArray());
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }

    }
}
