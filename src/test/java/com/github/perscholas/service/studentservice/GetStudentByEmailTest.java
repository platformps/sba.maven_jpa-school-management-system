package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
    @Before // TODO (OPTIONAL) - Use files to execute SQL commands
    public void setup() {
//        DirectoryReference directoryReference = DirectoryReference.RESOURCE_DIRECTORY;
//        File coursesSchemaFile = directoryReference.getFileFromDirectory("courses.create-table.sql");
//        File studentsSchemaFile = directoryReference.getFileFromDirectory("students.create-table.sql");
//        File coursesPopulatorFile = directoryReference.getFileFromDirectory("courses.populate-table.sql");
//        File studentsPopulatorFile = directoryReference.getFileFromDirectory("students.populate-table.sql");
//        File[] filesToExecute = new File[]{
//                coursesSchemaFile,
//                studentsSchemaFile,
//                coursesPopulatorFile,
//                studentsPopulatorFile
//        };
//        JdbcConfigurator.initialize();
    }

    @Test
    public void testGetStudentByEmailPresent() {

        StudentDao service = (StudentDao) new StudentService();

        StudentInterface student = service.getStudentByEmail("hluckham0@google.ru");
        Assert.assertNotNull(student);
    }
}
