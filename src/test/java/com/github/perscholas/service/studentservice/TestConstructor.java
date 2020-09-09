package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests


public class TestConstructor {

    @Test
    public void test() {
        //given
        Student expectedStudent = new Student();
        Course course = new Course();


        //when


        //then

        Assert.assertTrue(expectedStudent instanceof Student);
        Assert.assertTrue(course instanceof Course);
        Assert.assertTrue(course instanceof CourseInterface);


    }
}

