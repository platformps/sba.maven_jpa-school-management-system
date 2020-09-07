package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
    private StudentService studentService;
    
    @Before
    public void setup() {
        studentService = new StudentService(new StudentRepository(DatabaseConnection.getEntityManagerFactory(), new CourseRepository(DatabaseConnection.getEntityManagerFactory())));
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
    }
    
    @Test
    public void givenNoStudentsThenFindNoStudents() {
        // given
        // Students don't get populated
        int expected = 0;
        
        // when
        List<StudentInterface> actualStudents = studentService.getAllStudents();
        
        // then
        Assert.assertEquals(expected, actualStudents.size());
    }
    
    @Test
    public void givenStudentsThenFindByEmail() {
        // given
        DatabaseConnection.MANAGEMENT_SYSTEM.populate();
        
        // when
        List<StudentInterface> actualStudents = studentService.getAllStudents();
        
        // then : all students found should be able to be retrieved by getStudentByEmail, aka getStudentByEmail should never return null
        Assert.assertTrue(actualStudents.stream().map(StudentInterface::getEmail).map(studentService::getStudentByEmail).allMatch(Objects::nonNull));
    }
}
