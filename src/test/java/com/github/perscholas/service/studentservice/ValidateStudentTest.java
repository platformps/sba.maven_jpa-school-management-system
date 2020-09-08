package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */
public class ValidateStudentTest {
    private StudentService studentService;
    
    @Before
    public void setup() {
        JpaConfigurator.initialize();
        studentService = new StudentService(new StudentRepository(JpaConfigurator.getEntityManagerFactory(), new CourseRepository(JpaConfigurator.getEntityManagerFactory())));
    }
    
    @Test
    public void givenNullEmailThenFindNoStudentTest() {
        // given
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = null;
        String password = student.getPassword();
        
        // when
        boolean isValid = studentService.validateStudent(studentEmail, password);
        
        // then
        Assert.assertFalse(isValid);
    }
    
    @Test
    public void givenNullPasswordThenFindNoStudentTest() {
        // given
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        String password = null;
        
        // when
        boolean isValid = studentService.validateStudent(studentEmail, password);
        
        // then
        Assert.assertFalse(isValid);
    }
    
    @Test
    public void givenInvalidEmailThenFindNoStudentTest() {
        // given
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = "";
        Assert.assertTrue(!studentEmail.equals(student.getEmail()));
        String password = student.getPassword();
        
        // when
        boolean isValid = studentService.validateStudent(studentEmail, password);
        
        // then
        Assert.assertFalse(isValid);
    }
    
    @Test
    public void givenInvalidPasswordThenFindNoStudentTest() {
        // given
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        String password = "";
        Assert.assertTrue(!password.equals(student.getPassword()));
        
        // when
        boolean isValid = studentService.validateStudent(studentEmail, password);
        
        // then
        Assert.assertFalse(isValid);
    }
    
    @Test
    public void givenCorrectEmailAndPasswordThenValidTest(){
        // given
        StudentInterface student = studentService.getAllStudents().get(0);
        String studentEmail = student.getEmail();
        String password = student.getPassword();
    
        // when
        boolean isValid = studentService.validateStudent(studentEmail, password);
    
        // then
        Assert.assertTrue(isValid);
    }
}
