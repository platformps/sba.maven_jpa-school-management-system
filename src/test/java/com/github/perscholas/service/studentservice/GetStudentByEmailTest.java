package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFilesSource;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
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

    @ParameterizedTest
    @CsvFileSource(resources = "/students.csv", numLinesToSkip = 1)
    void test_forStudentsThatExist(String studentEmail, String name, String password) {

        String expectedStudentEmail = studentEmail;
        String expectedStudentName = name;
        String expectedStudentPassword = password;

        StudentDao Service = (StudentDao) new StudentService();
        StudentInterface student = service.getSTudentByEmail(studentEmail);

        String actualStudentEmail = student.getEmail();
        String actualStudentName = student.getName();
        String actualStudentPassword = student.getPassword();

        Assert.assertEquals(expectedStudentEmail, actualStudentEmail);
        Assert.assertEquals(expectedStudentName, actualStudentName);
        Assert.assertEquals(expectedStudentPassword, actualStudentPassword);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nonstudents.csv", numLinesToSkip = 1)
    void test_forStudentsThatDoNotExist(String studentEmail) {
    // given
        boolean expectedStudentIsFoundByEmail = false;


        // when
        // TODO - define `when` clause
        StudentDao service = (StudentDoa) new StudentService();
        StudentInterface student = service.getStudentByEmail(studentEmail);
        boolean actualStudentIsFoundByEmail = (student != null);


        // then
        // TODO - define `then` clause
        Assert.assertTrue(expectedStudentIsFoundByEmail == actualStudentIsFoundByEmail)
    }
}
