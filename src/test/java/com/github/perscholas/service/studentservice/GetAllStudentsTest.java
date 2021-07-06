package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:22 PM
 */
public class GetAllStudentsTest {
    @Before
    public void setup() {

        JdbcConfigurator.initialize(DatabaseConnection.UAT);
    }


    // given
    @Test
    public void test() {
        StudentDao service = new StudentService();
        // when
        List<StudentInterface> studentList = service.getAllStudents();

        // then
        Assert.assertEquals(studentList.size(),10);
    }

    @After
    public void destroy() throws SQLException {
        DatabaseConnection.UAT.getDatabaseConnection().close();
        DatabaseConnection.UAT.setName(null);
    }
}
