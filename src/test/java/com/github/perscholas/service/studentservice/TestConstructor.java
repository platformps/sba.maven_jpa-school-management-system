package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Student;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {

    //When
    @Test
    public void testStudentConstructor(){

        //Given
        Student student1 = new Student();
        //Student student1 = new Student("aiannitti7@is.gd","Alexandra Iannitti","TWP4hf5j");
        student1.setEmail("aiannitti7@is.gd");
        student1.setName("Alexandra Iannitti");
        student1.setPassword("TWP4hf5j");


        Student student2 = new Student("aiannitti7@is.gd","Alexandra Iannitti","TWP4hf5j");

        //Then
        Assert.assertEquals(student1.toString(),student2.toString());
      }

}
