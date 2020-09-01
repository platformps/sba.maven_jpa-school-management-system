package com.github.perscholas.service.studentservice;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {



    @Test
    public void testNulleryConstructor(){
        StudentDao service = new StudentService();

        Assert.assertNotNull(service.getAllStudents());

    }

}
