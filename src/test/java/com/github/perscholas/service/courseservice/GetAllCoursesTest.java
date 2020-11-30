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
        expectedCourses.add(new Course(1,"English", "Anderea Scamaden"));
        expectedCourses.add(new Course(2,"Mathematics", "Eustace Niemetz"));
        expectedCourses.add(new Course(3,"Anatomy", "Reynolds Pastor"));
        expectedCourses.add(new Course(4,"Organic Chemistry", "Odessa Belcher"));
        expectedCourses.add(new Course(5,"Physics", "Dani Swallow"));
        expectedCourses.add(new Course(6,"Digital Logic", "Glenden Reilingen"));
        expectedCourses.add(new Course(7,"Object Oriented Programming", "Giselle Ardy"));
        expectedCourses.add(new Course(8,"Data Structures", "Carolan Stoller"));
        expectedCourses.add(new Course(9,"Politics", "Carmita De Maine"));
        expectedCourses.add(new Course(10,"Art", "Kingsly Doxsey"));


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