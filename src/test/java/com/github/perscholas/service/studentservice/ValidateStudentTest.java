package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */


public class ValidateStudentTest {

    @Test
    public void testIsValid() {
        //given
        JdbcConfigurator.initialize();
        StudentDao service = new StudentService();
        String email = "hluckham0@google.ru"; //'hluckham0@google.ru', 'Hazel Luckham', 'X1uZcoIh0dj'
        String password = "X1uZcoIh0dj";
        Boolean expected=service.validateStudent(email,password);

        // when
        Boolean actual= service.getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(email) && students.getPassword().equals(password));

        // then
        Assert.assertTrue(expected.equals(actual));

    }
    @Test
    public void testNotValid() {
        //given
        JdbcConfigurator.initialize();
        StudentDao service = new StudentService();
        String email="rthgf@yht.com";
        String password="Hj8Kbn5";
        Boolean expected=service.validateStudent(email,password);

        // when
        Boolean actual= service.getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(email) && students.getPassword().equals(password));

        // then
        Assert.assertFalse(!expected.equals(actual));

    }

}