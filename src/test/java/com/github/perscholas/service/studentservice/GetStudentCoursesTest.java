package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.DatabaseConnection;
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
public class GetStudentCoursesTest {
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
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        DatabaseConnection.MANAGEMENT_SYSTEM.populate();
        
        // when
        List<StudentInterface> students = studentService.getAllStudents();
        Assert.assertTrue(students.size() > 0);
        students.stream().map(StudentInterface::getCourses).forEach(courses -> courses.stream().map(CourseInterface::toString).forEach(System.out::println));
        
        // then
        for(int index = 0; students.size() > index; index++){
            StudentInterface testStudent = students.get(index);
            
            System.out.println(testStudent);
            for (Course cours : testStudent.getCourses()) {
                System.out.println("HELLO"+cours);
            }
        }
        Assert.assertTrue(students.stream().map(StudentInterface::getCourses).map(List::size).allMatch(actualRegisteredCourseAmount -> {
            System.out.println(actualRegisteredCourseAmount);
            return expectedRegisteredCourseAmount == actualRegisteredCourseAmount;
        }));
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
        StudentInterface actualStudent = studentService.getStudentByEmail(studentEmail);
        List<Course> actualStudentCourses = actualStudent.getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();

        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
        Assert.assertTrue(actualStudentCourses.stream().map(Course::getId).allMatch(studentCourseId -> Objects.equals(courseId, studentCourseId)));
    }
    
    @Test
    public void givenStudentRegisterForSameCourseTwiceThenRegisterCourseAmount1Test() {
        // given
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        DatabaseConnection.MANAGEMENT_SYSTEM.populate();
        int expectedRegisteredCourseAmount = 1;
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        CourseInterface course = courseService.getAllCourses().get(0);
        int courseId = course.getId();
        
        // when
        System.out.println("REGISTERING "+studentEmail);
        studentService.registerStudentToCourse(studentEmail, courseId);
        studentService.registerStudentToCourse(studentEmail, courseId);
        StudentInterface actualStudent = studentService.getStudentByEmail(studentEmail);
        List<Course> actualStudentCourses = actualStudent.getCourses();
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
        StudentInterface actualStudent = studentService.getStudentByEmail(studentEmail);
        List<Course> actualStudentCourses = actualStudent.getCourses();
        int actualRegisteredCourseAmount = actualStudentCourses.size();

        // then
        Assert.assertEquals(expectedRegisteredCourseAmount, actualRegisteredCourseAmount);
        Assert.assertEquals(expectedCourses, actualStudentCourses);
    }
}
