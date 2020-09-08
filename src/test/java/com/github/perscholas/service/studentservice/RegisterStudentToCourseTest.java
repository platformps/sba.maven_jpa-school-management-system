package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:25 PM
 */
public class RegisterStudentToCourseTest {
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
        CourseInterface ExpectedRandomCourse = courses.get((int)(Math.random() * courses.size()));

        studentService.registerStudentToCourse(randomStudent.getEmail(), ExpectedRandomCourse.getId());
        List<CourseInterface> actualStudentCourses = studentService.getStudentCourses(randomStudent.getEmail());

        // then
        Assert.assertEquals(ExpectedRandomCourse, actualStudentCourses.get(0));
    }
}
