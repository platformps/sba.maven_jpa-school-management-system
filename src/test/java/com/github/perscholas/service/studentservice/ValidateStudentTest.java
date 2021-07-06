package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */ // TODO - Define tests
public class ValidateStudentTest {

    @Before
    public void setup(){

        JdbcConfigurator.initialize(DatabaseConnection.UAT);
    }

    @Test
    public void testValidateStudent(){
        //when
        StudentDao service = new StudentService();
        //then
        Assert.assertFalse(service.validateStudent("aiannitti7@is.gd",""));
        Assert.assertTrue(service.validateStudent("aiannitti7@is.gd","TWP4hf5j"));
        Assert.assertTrue(service.validateStudent("cjaulme9@bing.com","FnVklVgC6r6"));
    }
    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }
}
