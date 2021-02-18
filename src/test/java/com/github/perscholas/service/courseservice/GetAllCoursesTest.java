package com.github.perscholas.service.courseservice;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
    
    private CourseService courseService;
    
    @Before
    public void setup() {
        JpaConfigurator.initialize();
        courseService = new CourseService(new CourseRepository(JpaConfigurator.getEntityManagerFactory()));
    }

    @Test
    public void givenNoCoursesInDatabaseTest() {
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        int expectedSize = 0;
        
        // when
        List<CourseInterface> actualCourses = courseService.getAllCourses();


        // then
        Assert.assertEquals(expectedSize , actualCourses.size());
    }
    
    @Test
    public void givenWhenCoursesAreInitialized(){
        //given
        // populated during initialization
        
        //when
        List<CourseInterface> actualCourses = courseService.getAllCourses();
    
        // then
        Assert.assertTrue(actualCourses.size() > 0);
    }
}
