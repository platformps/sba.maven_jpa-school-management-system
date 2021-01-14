package com.github.perscholas.service.studentservice;

import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:22 PM
 */
public class GetAllStudentsTest {
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


    @Test
    public void test_forAllStudents() {
        //given
        List<StudentInterface> expectedStudents = new ArrayList<StudentInterface>();
        Path path = Paths.get(System.getProperty("user.dir") + "/src/test/resources/allstudents.csv");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            while(bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String [] arr = line.split(",");
                StudentInterface student = new Student(arr[0],arr[1],arr[2]);
                expectedStudents.add(student);
            }
            expectedStudents.sort((s1,s2) -> s1.getEmail().compareTo(s2.getEmail()));
        }
        catch (IOException e) {

        }


        //when
        StudentDao service = (StudentDao) new StudentService();
        List<StudentInterface> actualStudents = service.getAllStudents();
        actualStudents.sort((s1,s2) -> s1.getEmail().compareTo(s2.getEmail()));


        //then
        Assert.assertArrayEquals(expectedStudents.toArray(), actualStudents.toArray());
    }
}
