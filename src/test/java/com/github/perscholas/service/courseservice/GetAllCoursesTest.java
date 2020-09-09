package com.github.perscholas.service.courseservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
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
    public void test() {
        JdbcConfigurator.initialize();
        DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;

        // when
        // TODO - define `when` clause
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM course");

        List<CourseInterface> expectedCourses = new ArrayList<>();
        Course eCourse = new Course();
        eCourse.setId(1);
        eCourse.setName("English");
        eCourse.setInstructor("Anderea Scamaden");
        expectedCourses.add(eCourse);

        eCourse.setId(2);
        eCourse.setName("Mathematics");
        eCourse.setInstructor("Eustace Niemetz");
        expectedCourses.add(eCourse);

        eCourse.setId(3);
        eCourse.setName("Anatomy");
        eCourse.setInstructor("Reynolds Pastor");
        expectedCourses.add(eCourse);

        eCourse.setId(4);
        eCourse.setName("Organic Chemistry");
        eCourse.setInstructor("Odessa Belcher");
        expectedCourses.add(eCourse);

        eCourse.setId(5);
        eCourse.setName("Physics");
        eCourse.setInstructor("Dani Swallow");
        expectedCourses.add(eCourse);

        eCourse.setId(6);
        eCourse.setName("Digital Logic");
        eCourse.setInstructor("Glenden Reilingen");
        expectedCourses.add(eCourse);

        eCourse.setId(7);
        eCourse.setName("Object Oriented Programming");
        eCourse.setInstructor("Giselle Ardy");
        expectedCourses.add(eCourse);

        eCourse.setId(8);
        eCourse.setName("Data Structures");
        eCourse.setInstructor("Carolan Stoller");
        expectedCourses.add(eCourse);

        eCourse.setId(9);
        eCourse.setName("Politics");
        eCourse.setInstructor("Carmita De Maine");
        expectedCourses.add(eCourse);

        eCourse.setId(10);
        eCourse.setName("Art");
        eCourse.setInstructor("Kingsly Doxsey");
        expectedCourses.add(eCourse);


        List<CourseInterface> actualCourses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Course aCourse = new Course();
                aCourse.setId(resultSet.getInt("id"));
                aCourse.setName(resultSet.getString("name"));
                aCourse.setInstructor(resultSet.getString("instructor"));
                actualCourses.add(aCourse);
            }
            //return courseInterfaceList;
        } catch (Exception e) {
            throw new Error(e);
        }

        // then
        // TODO - define `then` clause
        Assert.assertArrayEquals(expectedCourses.toArray(), actualCourses.toArray());
    }
}