package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
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
 * @created 02/12/2020 - 8:25 PM
 */
public class GetStudentCoursesTest {
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
        JdbcConfigurator.initialize(DatabaseConnection.UAT);
    }

    // given
    @Test
    public void test() {
        StudentDao service = new StudentService();

        // when
        List<CourseInterface> courses = service.getStudentCourses("aiannitti7@is.gd");
        // then
        Assert.assertEquals(courses.get(0).getName(),"English");
        Assert.assertEquals(courses.get(1).getName(),"Mathematics");
        Assert.assertEquals(courses.get(2).getName(),"Anatomy");
    }

    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }
}
