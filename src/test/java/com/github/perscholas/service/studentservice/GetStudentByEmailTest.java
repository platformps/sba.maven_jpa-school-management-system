package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:23 PM
 */
public class GetStudentByEmailTest {
    @BeforeAll // TODO (OPTIONAL) - Use files to execute SQL commands
    public static void setup() {
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

        JdbcConfigurator.initialize();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/students.csv", numLinesToSkip = 1)
    void test_forStudentsThatExist(String studentEmail, String name, String password) {
        //given
        String expectedStudentEmail = studentEmail;
        String expectedStudentName = name;
        String expectedStudentPassword = password;

        //when
        StudentDao service = (StudentDao) new StudentService();
        StudentInterface student = service.getStudentByEmail(studentEmail);

        String actualStudentEmail = student.getEmail();
        String actualStudentName = student.getName();
        String actualStudentPassword = student.getPassword();

        //then
        Assert.assertEquals(expectedStudentEmail, actualStudentEmail);
        Assert.assertEquals(expectedStudentName, actualStudentName);
        Assert.assertEquals(expectedStudentPassword, actualStudentPassword);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nonstudents.csv", numLinesToSkip = 1)
    void test_forStudentsThatDoNotExist(String studentEmail) {
        //given
        boolean expectedStudentIsFoundByEmail = false;

        //when
        StudentDao service = (StudentDao) new StudentService();
        StudentInterface student = service.getStudentByEmail(studentEmail);
        boolean actualStudentIsFoundByEmail = ( student != null );

        //then
        Assert.assertTrue(expectedStudentIsFoundByEmail == actualStudentIsFoundByEmail);
    }
}
