package com.github.perscholas.service.studentservice;

import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {

    @Test
  //Given
    public void nullaryTest() {
        StudentService service = new StudentService();


        Assert.assertNotNull(service.getAllStudents());

    }
}
