package com.github.perscholas.service.courseservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
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
    @Test
    public void numberOfCoursesTest() {
        JdbcConfigurator.initialize();
        CourseService cs = new CourseService();
        int expectedNumberOfCourses = 10;

        // when
        int actualNumberOfCourses = cs.getAllCourses().size();

        // then
        Assert.assertEquals(expectedNumberOfCourses, actualNumberOfCourses);
    }

    @Test
    public void courseNamesTest() {
        //given
        CourseService cs = new CourseService();
        List<CourseInterface> courseInterfaceList = cs.getAllCourses();
        String[] expectedCourseNames = {"English", "Mathematics", "Anatomy", "Organic Chemistry", "Physics",
                                        "Digital Logic", "Object Oriented Programming", "Data Structures", "Politics", "Art"};

        //when
        String[] actualCourseNames = courseInterfaceList.stream()
                .map(CourseInterface::getName)
                .toArray(String[]::new);

        //then
        Assert.assertArrayEquals(expectedCourseNames, actualCourseNames);
    }
}