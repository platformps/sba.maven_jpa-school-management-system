package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:25 PM
 */
public class RegisterStudentToCourseTest {
    private StudentService studentService;
    private CourseService courseService;
    
    @Before
    public void setup() {
        JpaConfigurator.initialize();
        CourseRepository courseRepository = new CourseRepository(JpaConfigurator.getEntityManagerFactory());
        StudentRepository studentRepository = new StudentRepository(JpaConfigurator.getEntityManagerFactory(), courseRepository);
        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
    }
    
    @Test
    public void givenStudentHasNotRegisteredForCourseThenShouldBeNoCoursesTest() {
        // given
        int expectedRegisteredCourseAmount = 0;
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        
        // when
        List<Course> actualStudentCourses = studentService.getStudentByEmail(studentEmail).getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();
        
        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
    }
    
    @Test
    public void givenStudentRegisterForJustOneCourseThenRegisteredCourseAmount1Test() {
        // given
        int expectedRegisteredCourseAmount = 1;
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        CourseInterface course = courseService.getAllCourses().get(0);
        int courseId = course.getId();
        
        // when
        studentService.registerStudentToCourse(studentEmail, courseId);
        List<Course> actualStudentCourses = studentService.getStudentByEmail(studentEmail).getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();
        
        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
        Assert.assertTrue(actualStudentCourses.stream().map(Course::getId).allMatch(studentCourseId -> Objects.equals(courseId, studentCourseId)));
    }
    
    @Test
    public void givenStudentRegisterForSameCourseTwiceThenRegisterCourseAmount1Test() {
        // given
        int expectedRegisteredCourseAmount = 1;
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        CourseInterface course = courseService.getAllCourses().get(0);
        int courseId = course.getId();
        
        // when
        studentService.registerStudentToCourse(studentEmail, courseId);
        studentService.registerStudentToCourse(studentEmail, courseId);
        List<Course> actualStudentCourses = studentService.getStudentByEmail(studentEmail).getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();
        
        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
        Assert.assertTrue(actualStudentCourses.stream().map(Course::getId).allMatch(studentCourseId -> Objects.equals(courseId, studentCourseId)));
    }
    
    @Test
    public void givenStudentRegisterForTwoDifferentCoursesThenRegisterCourseAmount2Test() {
        // given
        int expectedRegisteredCourseAmount = 2;
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        CourseInterface course = courseService.getAllCourses().get(0);
        CourseInterface secondCourse = courseService.getAllCourses().get(1);
        List<CourseInterface> expectedCourses = Arrays.asList(course, secondCourse);
        
        
        // when
        expectedCourses.stream().map(CourseInterface::getId).forEach(courseId -> studentService.registerStudentToCourse(studentEmail, courseId));
        List<Course> actualStudentCourses = studentService.getStudentByEmail(studentEmail).getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();
        
        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
        Assert.assertEquals(expectedCourses, actualStudentCourses);
    }
}
