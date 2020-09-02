package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:25 PM
 */
public class RegisterStudentToCourseTest {
    @Before
    public void setup() {
        JdbcConfigurator.initialize(DatabaseConnection.UAT);
        };

    // given
    @After
    @Test
    public void test() {
        StudentDao service = new StudentService();
        //when
        service.registerStudentToCourse("cstartin3@flickr.com",3);

        // then
        Assert.assertEquals(service.getStudentCourses("cstartin3@flickr.com").get(0).getName(),"Anatomy");
    }

    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }
}
