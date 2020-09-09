package com.github.perscholas.service.courseservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
        JdbcConfigurator.initialize();
    }

    @Test
   public void testGetAllStudentCourse(){
       CourseDao studentService = new CourseService();

       List<CourseInterface> allCourses = studentService.getAllCourses();

       Assert.assertNotNull(allCourses);
   }


}
