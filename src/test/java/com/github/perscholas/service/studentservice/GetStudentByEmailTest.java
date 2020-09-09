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
        expectedEmail.add(new Student("aiannitti7@is.gd","Alexandra Iannitti", "TWP4hf5j"));
        expectedEmail.add(new Student("cjaulme9@bing.com","Cahra Jaulme", "FnVklVgC6r6"));
        expectedEmail.add(new Student("cstartin3@flickr.com","Clem Startin", "XYHzJ1S"));
        expectedEmail.add(new Student("hguerre5@deviantart.com","Harcourt Guerre", "OzcxzD1PGs"));
        expectedEmail.add(new Student("hluckham0@google.ru","Hazel Luckham", "X1uZcoIh0dj"));
        expectedEmail.add(new Student("htaffley6@columbia.edu","Holmes Taffley", "xowtOQ"));
        expectedEmail.add(new Student("ljiroudek8@sitemeter.com","Laryssa Jiroudek", "bXRoLUP"));
        expectedEmail.add(new Student("qllorens2@howstuffworks.com","Quillan Llorens", "W6rJuxd"));
        expectedEmail.add(new Student("sbowden1@yellowbook.com","Sonnnie Bowden", "SJc4aWSU"));
        expectedEmail.add(new Student("tattwool4@biglobe.ne.jp","Thornie Attwool", "Hjt0SoVmuBz"));
        expectedEmail.add(new Student("x","x", "x"));


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
