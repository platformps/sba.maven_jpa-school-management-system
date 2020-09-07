package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
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
public class RegisterStudentToCourseTest {
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
    @Test
    public void test() {
        //JdbcConfigurator.initialize();
        StudentDao studentDao = new StudentService();
        List<CourseInterface> courseInterfaceList;

        // when
        studentDao.registerStudentToCourse("sbowden1@yellowbook.com", 7);
        courseInterfaceList = studentDao.getStudentCourses("sbowden1@yellowbook.com");

        // then
        Assert.assertTrue(courseInterfaceList.size() > 0);
    }

    @Test
    public void test2() {
        StudentDao studentDao = new StudentService();
        List<CourseInterface> courseInterfaceList;

        // when
        courseInterfaceList = studentDao.getStudentCourses("hguerre5@deviantart.com");

        // then
        Assert.assertTrue(courseInterfaceList.size() == 0);
    }

    @Test
    public void test3() {
        StudentDao studentDao = new StudentService();
        List<CourseInterface> courseInterfaceList;

        // when
        studentDao.registerStudentToCourse("ljiroudek8@sitemeter.com", 8);
        studentDao.registerStudentToCourse("ljiroudek8@sitemeter.com", 8);
        courseInterfaceList = studentDao.getStudentCourses("ljiroudek8@sitemeter.com");

        // then
        Assert.assertTrue(courseInterfaceList.size() == 1);
    }
}
