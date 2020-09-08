package com.github.perscholas.service.studentservice;

import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.service.StudentService;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */
public class TestConstructor {
    
    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidArgumentsThenThrowNPETest() {
        // given
        CourseRepository courseRepository = null;
        StudentRepository studentRepository = null;
        
        // when
        StudentService studentService = new StudentService(studentRepository);
        
        // then
        // exception
        
    }
    
    @Test
    public void givenValidArgumentsThenCreatedTest(){
        // given
        CourseRepository courseRepository = new CourseRepository(JpaConfigurator.getEntityManagerFactory());
        StudentRepository studentRepository = new StudentRepository(JpaConfigurator.getEntityManagerFactory(), courseRepository);
    
        // when
        StudentService studentService = new StudentService(studentRepository);
    
        // then
        // no exceptions
    }
}
