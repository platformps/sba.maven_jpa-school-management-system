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
    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao studentService = new StudentService();
        CourseDao courseService = new CourseService();

        // when
        List<StudentInterface> students = studentService.getAllStudents();
        List<CourseInterface> courses = courseService.getAllCourses();
        StudentInterface randomStudent = students.get((int)(Math.random() * students.size()));
        Set<CourseInterface> expectedStudentCoursesSet = new HashSet<>();

        for (int i = 0; i <= 3; i++) {
            CourseInterface randomCourse = courses.get((int)(Math.random() * courses.size()));
            expectedStudentCoursesSet.add(randomCourse);
            studentService.registerStudentToCourse(randomStudent.getEmail(), randomCourse.getId());
        }

        List<CourseInterface> actualStudentCourses = studentService.getStudentCourses(randomStudent.getEmail());
        Course[] actualStudentCoursesArray = actualStudentCourses.toArray(new Course[actualStudentCourses.size()]);

        // then
        Comparator<Course> compareCourse = (Course c1, Course c2) -> c1.getId().compareTo(c2.getId());
        Course[] expectedStudentCoursesArray = expectedStudentCoursesSet.toArray(new Course[expectedStudentCoursesSet.size()]);

        Arrays.sort(expectedStudentCoursesArray, compareCourse);
        Arrays.sort(actualStudentCoursesArray, compareCourse);

        Assert.assertArrayEquals(expectedStudentCoursesArray, actualStudentCoursesArray);
    }
}
