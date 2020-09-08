package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {

    @Test
    public void courseConstructorTest() {
        //when
        Course course = new Course();

        //then
        Assert.assertTrue(course instanceof Course);
    }

    @Test
    public void studentConstructorTest() {
        //when
        Student student = new Student();

        //then
        Assert.assertTrue(student instanceof Student);
        Assert.assertNotNull(student.getStudentCourses());
    }
}
