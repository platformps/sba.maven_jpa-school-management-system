package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.h2.engine.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:22 PM
 */
public class GetAllStudentsTest {
    private static final StudentService STUDENT_SERVICE = new StudentService(new StudentRepository(DatabaseConnection.getEntityManagerFactory(), new CourseRepository(DatabaseConnection.getEntityManagerFactory())));
    
    @Before
    public void setup() {
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
    }
    
    @Test
    private void givenNoStudentsInDatabaseTest() {
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        int expectedSize = 0;
        
        // when
        List<StudentInterface> actualStudents = STUDENT_SERVICE.getAllStudents();
        
        
        // then
        Assert.assertEquals(expectedSize , actualStudents.size());
    }
    
    @Test
    private void givenWhenStudentsAreInitialized(){
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        
        //when
        List<StudentInterface> actualStudents = STUDENT_SERVICE.getAllStudents();
        
        // then
        Assert.assertTrue(actualStudents.size() > 0);
    }
}
