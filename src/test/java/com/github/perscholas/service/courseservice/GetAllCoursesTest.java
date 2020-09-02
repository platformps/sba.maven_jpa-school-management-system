package com.github.perscholas.service.courseservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
    @Before
    public void setup() {
        JdbcConfigurator.initialize(DatabaseConnection.UAT);
    }

   @Test
    public void test() {
        CourseDao service = new CourseService();
        // when
        List<CourseInterface> courses = service.getAllCourses();


        // then
        Assert.assertEquals(courses.size(),10);
        Assert.assertEquals(courses.get(0).getName(),"English");
    }
    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }

}
