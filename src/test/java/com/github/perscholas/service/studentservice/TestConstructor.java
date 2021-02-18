package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
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
        Assert.assertTrue(courses instanceof CourseInterface);
    }

    @Test
    public void studentConstructorTest() {
        //when
        Student students = new Student();

        //then
        Assert.assertTrue(students instanceof StudentInterface);
    }
     //given


    @Test
    public void studentConstructorNotNullaryTest() {
        Student student = new Student();

        Assert.assertNotNull(student.getStudentCourses());
    }
}