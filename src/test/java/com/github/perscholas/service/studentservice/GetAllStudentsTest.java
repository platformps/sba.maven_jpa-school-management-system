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

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:22 PM
 */
public class GetAllStudentsTest {
    private StudentService studentService;
    
    @Before
    public void setup() {
        JpaConfigurator.initialize();
        studentService = new StudentService(new StudentRepository(JpaConfigurator.getEntityManagerFactory(), new CourseRepository(JpaConfigurator.getEntityManagerFactory())));
    }
    
    @Test
    public void givenNoStudentsInDatabaseTest() {
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        
        int expectedSize = 0;
        
        // when
        List<StudentInterface> actualStudents = studentService.getAllStudents();
        
        
        // then
        Assert.assertEquals(expectedSize, actualStudents.size());
    }
    
    @Test
    public void givenWhenStudentsAreInitializedTest() {
        //given
        // populated on initialization
        
        //when
        List<StudentInterface> actualStudents = studentService.getAllStudents();
        
        // then
        Assert.assertTrue(actualStudents.size() > 0);
    }
}
