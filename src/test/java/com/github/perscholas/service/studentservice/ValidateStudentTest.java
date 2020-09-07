package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */ // TODO - Define tests
public class ValidateStudentTest {
    @Test
    public void test() {
        //given
        //JdbcConfigurator.initialize();
        StudentDao studentDao = new StudentService();
        Boolean validate;

        //when
        validate = studentDao.validateStudent("qllorens2@howstuffworks.com", "W6rJuxd");

        //then
        Assert.assertTrue(validate);
    }
}
