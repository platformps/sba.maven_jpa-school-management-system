package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {
    @Test
    public void nullConstructorTest() {
        //given
        StudentInterface studentInterface = new Student();
        String[] expected = {null, null, null};
        String[] actual = new String[3];
        //when
        actual[0] = studentInterface.getEmail();
        actual[1] = studentInterface.getName();
        actual[2] = studentInterface.getPassword();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parameterizedConstructorTest() {
        //given
        StudentInterface studentInterface = new Student("testemail@yahoo.com", "Brad Pitt", "xyZ123");
        String[] expected = {"testemail@yahoo.com", "Brad Pitt", "xyZ123"};
        String[] actual = new String[3];
        //when
        actual[0] = studentInterface.getEmail();
        actual[1] = studentInterface.getName();
        actual[2] = studentInterface.getPassword();

        //then
        Assert.assertEquals(expected, actual);
    }
}
