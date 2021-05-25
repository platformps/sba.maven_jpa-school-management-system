package com.github.perscholas.service.courseservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:26 PM
 */
public class GetAllCoursesTest {
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
        JdbcConfigurator.initialize();
        CourseDao service = new CourseService();
        int expected= 10;

        // when
        // TODO - define `when` clause
      List<CourseInterface> courses= service.getAllCourses();
      int actual=courses.size();


        // then
        // TODO - define `then` clause
        assertEquals(actual,expected);
    }
}
