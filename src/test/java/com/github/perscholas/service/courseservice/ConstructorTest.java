package com.github.perscholas.service.courseservice;

import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.service.CourseService;
import org.junit.Test;

public class ConstructorTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidArgumentsThenThrowNPETest() {
        // given
        CourseRepository courseRepository = null;
        
        // when
        CourseService courseService = new CourseService(courseRepository);
        
        // then
        // exception
        
    }
    
    @Test
    public void givenValidArgumentsThenCreatedTest() {
        // given
        CourseRepository courseRepository = new CourseRepository(JpaConfigurator.getEntityManagerFactory());
        
        // when
        CourseService studentService = new CourseService(courseRepository);
        
        // then
        // no exceptions
    }
}
