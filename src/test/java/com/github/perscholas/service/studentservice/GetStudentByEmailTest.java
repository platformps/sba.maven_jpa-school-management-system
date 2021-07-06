package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
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
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
    @Before
    public void setup() {

        JdbcConfigurator.initialize(DatabaseConnection.UAT);
    }

    // given
    @Test
    public void test() {
        StudentDao service = new StudentService();

        // when
        StudentInterface student1 = service.getStudentByEmail("aiannitti7@is.gd");
        // then
        Assert.assertEquals(student1.getEmail(),"aiannitti7@is.gd");
        Assert.assertEquals(student1.getName(),"Alexandra Iannitti");
        Assert.assertEquals(student1.getPassword(),"TWP4hf5j");
    }

    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }
}
