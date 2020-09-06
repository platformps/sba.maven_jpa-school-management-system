package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import java.io.File;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {


    @Test
    public void courseConstructorTest() {
        /// given


        //when
        Course courses = new Course();

        //then
        Assert.assertTrue(courses instanceof Course);
    }

    @Test
    public void studentConstructorTest() {
        //when
        Student students = new Student();

        //then
        Assert.assertTrue(students instanceof Student);
    }
     //given
    @Test
    public void courseNotNullaryConstructorTest() {

        //when
        Course courses = new Course();
        Integer actualId = courses.getId();
        String actualName = courses.getName();
        String actualInstructor = courses.getInstructor();

        //then
        Assert.assertNotNull(actualId);
        Assert.assertNotNull(actualName);
        Assert.assertNotNull(actualInstructor);
       }

    //given
    @Test
    public void studentNotNullaryConstructorTest() {
        //given
        Student students = new Student();


        //when
        String actualEmail = students.getEmail();
        String actualName = students.getName();
        String actualPassword = students.getPassword();

        //then
        Assert.assertNotNull(actualEmail);
        Assert.assertNotNull(actualName);
        Assert.assertNotNull(actualPassword);
    }
}