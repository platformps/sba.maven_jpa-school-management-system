package com.github.perscholas.service.courseservice;

import com.github.perscholas.config.DatabaseConnection;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
    
    private static final CourseService COURSE_SERVICE = new CourseService(new CourseRepository(DatabaseConnection.getEntityManagerFactory()));
    
    @Before
    public void setup() {
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
    }

    @Test
    private void givenNoCoursesInDatabaseTest() {
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.drop();
        int expectedSize = 0;
        
        // when
        List<CourseInterface> actualCourses = COURSE_SERVICE.getAllCourses();


        // then
        Assert.assertEquals(expectedSize , actualCourses.size());
    }
    
    @Test
    private void givenWhenCoursesAreInitialized(){
        //given
        DatabaseConnection.MANAGEMENT_SYSTEM.create();
        
        //when
        List<CourseInterface> actualCourses = COURSE_SERVICE.getAllCourses();
    
        // then
        Assert.assertTrue(actualCourses.size() > 0);
    }
}
