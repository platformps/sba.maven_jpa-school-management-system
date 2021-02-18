package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:25 PM
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
    /**
     * This method takes a Student’s Email as a parameter and would find all the courses a student is registered.
     * @param studentEmail - student's email to be parsed
     * @return list of courses student has registered to
     */

    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao studentService = new StudentService();
        CourseDao courseService = new CourseService();

        // when
        // TODO - define `when` clause
        //Get the list of students
        List<StudentInterface> studentList = studentService.getAllStudents();
        //Get the list of courses
        List<CourseInterface> courseList = courseService.getAllCourses();
        //Make an Map of students and courses

        Map<StudentInterface,CourseInterface> mapfromLists = IntStream.range(0, studentList.size()).collect(HashMap::new, (m, i) ->
                m.put(studentList.get(i),courseList.get(i)), Map::putAll);
        //List<CourseInterface> actualList = getStudentCourses("htaffley6@columbia.edu");
                // then
        // TODO - define `then` clause

        Assert.assertNotNull(mapfromLists);
    }
}

