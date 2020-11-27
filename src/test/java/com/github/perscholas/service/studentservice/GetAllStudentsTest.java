package com.github.perscholas.service.studentservice;

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
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:22 PM
 */
public class GetAllStudentsTest {
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
       //JdbcConfigurator.initialize();
    }

    @Test
    public void testGetAllStudentTest() {

        StudentDao service = new StudentService();
        List<StudentInterface> studentList = service.getAllStudents();
        Assert.assertNotNull(studentList);
    }
}
