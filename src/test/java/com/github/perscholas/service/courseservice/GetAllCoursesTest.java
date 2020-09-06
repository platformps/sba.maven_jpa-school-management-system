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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
class GetAllCoursesTest {
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

<<<<<<< HEAD
=======

>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
    @Test
    public void test() {
        // given
        JdbcConfigurator.initialize();

        List<CourseInterface> listExpectedCourses = new ArrayList<>();
        ResultSet resultSet = DatabaseConnection.MANAGEMENT_SYSTEM.executeQuery("SELECT * FROM course");

        List<CourseInterface> listActualCourses = new ArrayList<>();

        try {
            while(resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setInstructor(resultSet.getString("instructor"));
                listActualCourses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // then
        // TODO - define `then` clause

        //Assert.assertEquals(expectedCourses.toArray(), actualCourses.toArray());     Deprecated

        Assert.assertArrayEquals(listExpectedCourses.toArray(), listActualCourses.toArray());
    }
    }


