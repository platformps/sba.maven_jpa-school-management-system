package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.SchoolManagementSystem;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
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
 * @created 02/12/2020 - 8:25 PM
 */
public class GetStudentCoursesTest {
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
        DirectoryReference directoryReference = DirectoryReference.RESOURCE_DIRECTORY;
        File coursesSchemaFile = directoryReference.getFileFromDirectory("courses.create-table.sql");
        File studentsSchemaFile = directoryReference.getFileFromDirectory("students.create-table.sql");
        File coursesPopulatorFile = directoryReference.getFileFromDirectory("courses.populate-table.sql");
        File studentsPopulatorFile = directoryReference.getFileFromDirectory("students.populate-table.sql");
        File[] filesToExecute = new File[]{
                coursesSchemaFile,
                studentsSchemaFile,
                coursesPopulatorFile,
                studentsPopulatorFile
        };
    }

    // given
    // TODO - Add `@Test` annotation
    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao service = new StudentService();
        service.registerStudentToCourse("hluckham0@google.ru",1);
        service.registerStudentToCourse("hluckham0@google.ru",2);
        service.registerStudentToCourse("hluckham0@google.ru",3);


        // when
        // TODO - define `when` clause
       List<CourseInterface> courses= service.getStudentCourses("hluckham0@google.ru");



        // then
        // TODO - define `then` clause
        Assert.assertEquals(courses.get(0).getName(),"English");
        Assert.assertEquals(courses.get(1).getName(),"Mathematics");
        Assert.assertEquals(courses.get(2).getName(),"Anatomy");
    }
}
