package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:24 PM
 */ // TODO - Define tests
public class ValidateStudentTest {

    @Test
    public void test() {
        // given
        JdbcConfigurator.initialize();
        StudentDao studentService = new StudentService();

        // when
        List<StudentInterface> students = studentService.getAllStudents();
        StudentInterface expectedStudent = students.get((int)(Math.random() * students.size()));

        // then
        Boolean didStudentRegister = studentService.validateStudent(expectedStudent.getEmail(), expectedStudent.getPassword());
        Assert.assertTrue(didStudentRegister);
    }
}
