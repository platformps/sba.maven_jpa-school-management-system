package com.github.perscholas.service.studentservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.DirectoryReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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

    // given
    // TODO - Add `@Test` annotation
    @Test
    public void test() {
        JdbcConfigurator.initialize();
        StudentDao studentService = new StudentService();
        DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;

        // when
        // TODO - define `when` clause
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");

        List<StudentInterface> expectedEmail = new ArrayList<>();
        Student eEmail = new Student();
        eEmail.setEmail("hluckham0@google.ru".toString());
        eEmail.setName("Hazel Luckham".toString());
        eEmail.setPassword("X1uZcoIh0dj".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("sbowden1@yellowbook.com".toString());
        eEmail.setName("Sonnnie Bowden".toString());
        eEmail.setPassword("SJc4aWSU".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("qllorens2@howstuffworks.com".toString());
        eEmail.setName("Quillan Llorens".toString());
        eEmail.setPassword("W6rJuxd".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("cstartin3@flickr.com".toString());
        eEmail.setName("Clem Startin".toString());
        eEmail.setPassword("XYHzJ1S".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("tattwool4@biglobe.ne.jp".toString());
        eEmail.setName("Thornie Attwool".toString());
        eEmail.setPassword("Hjt0SoVmuBz".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("hguerre5@deviantart.com".toString());
        eEmail.setName("Harcourt Guerre".toString());
        eEmail.setPassword("OzcxzD1PGs".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("htaffley6@columbia.edu".toString());
        eEmail.setName("Holmes Taffley".toString());
        eEmail.setPassword("xowtOQ".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("aiannitti7@is.gd".toString());
        eEmail.setName("Alexandra Iannitti".toString());
        eEmail.setPassword("TWP4hf5j".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("ljiroudek8@sitemeter.com".toString());
        eEmail.setName("Laryssa Jiroudek".toString());
        eEmail.setPassword("bXRoLUP".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("cjaulme9@bing.com".toString());
        eEmail.setName("Cahra Jaulme".toString());
        eEmail.setPassword("FnVklVgC6r6".toString());
        expectedEmail.add(eEmail);

        eEmail.setEmail("x".toString());
        eEmail.setName("x".toString());
        eEmail.setPassword("x".toString());
        expectedEmail.add(eEmail);


        List<StudentInterface> actualEmail = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student aEmail = new Student();
                aEmail.setEmail(resultSet.getString("email").toString());
                aEmail.setName(resultSet.getString("name").toString());
                aEmail.setPassword(resultSet.getString("password").toString());
                actualEmail.add(aEmail);
            }
            //return courseInterfaceList;
        } catch (Exception e) {
            throw new Error(e);
        }

        // then
        // TODO - define `then` clause
        Assert.assertEquals(expectedEmail.toArray(), actualEmail.toArray());
    }
}
