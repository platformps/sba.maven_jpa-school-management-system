package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */ // TODO - Define tests
public class ValidateStudentTest {
    // TODO - Add `@Test` annotation
    @Test
    public void validtest() {
        JdbcConfigurator.initialize();
        StudentDao service = new StudentService();
        String email="qllorens2@howstuffworks.com";
        String password="W6rJuxd";
        Boolean expected=service.validateStudent(email,password);
        // when

        Boolean actual= service.getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(email) && students.getPassword().equals(password));
        // then
        // TODO - define _then_ clause
        Assert.assertTrue(expected.equals(actual));

    }
    @Test
    public void nonValidtest() {
        JdbcConfigurator.initialize();
        StudentDao service = new StudentService();
        String email="rtheyte";
        String password="W6rJuxd";
        Boolean expected=service.validateStudent(email,password);
        // when

        Boolean actual= service.getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(email) && students.getPassword().equals(password));
        // then
        // TODO - define _then_ clause
        Assert.assertFalse(!expected.equals(actual));

    }

}
