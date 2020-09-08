package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.*;
import com.github.perscholas.utils.IOConsole;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    private final DatabaseConnection dbc;
    private static final IOConsole console = new IOConsole();

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentService() {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            List<StudentInterface> studentList = new ArrayList<>();
            while (resultSet.next()) {
                StudentInterface student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                studentList.add(student);
            }

            return studentList; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> list = getAllStudents();
        for (StudentInterface studentInterface : list) {
            if (studentInterface.getEmail().equals(studentEmail)) {
                //System.out.println(studentInterface);
                return studentInterface;
            }
        }
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        Boolean isValid = getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(studentEmail) && students.getPassword().equals(password));
        // password.equals(Objects.requireNonNull(getStudentByEmail(studentEmail)).getPassword());
        return isValid;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        List<CourseInterface> courseList = new CourseService().getAllCourses();
        if (getStudentCourses(studentEmail).size() != 0) {
            for (CourseInterface course : getStudentCourses(studentEmail)) {
                if (course.getId() != courseId) {
                    studentRegisterCourse(studentEmail, courseId);
                } else
                    console.println("Already registered to course");
            }
        } else
            studentRegisterCourse(studentEmail, courseId);

    }


    public void studentRegisterCourse(String studentEmail, int courseId) {
        String query = "insert into StudentCourse (email,courseId ) values (?,?)";
        try {
            PreparedStatement preparedStatement = dbc.getDatabaseConnection().prepareStatement(query);
            preparedStatement.setString(1, studentEmail);
            preparedStatement.setInt(2, courseId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            console.println("Error, cannot register");
        }
    }


    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> courseList = new ArrayList<>();
        String query = "Select c.id,c.name,c.instructor" +
                " from course c, studentcourse sc where sc.email='" + studentEmail + "' and c.id=sc.courseId";
        try {
            Statement statement = dbc.getDatabaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CourseInterface course = new Course();
                course.setId(resultSet.getInt(1));
                course.setName(resultSet.getString(2));
                course.setInstructor(resultSet.getString(3));
                courseList.add(course);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            console.println("Error, cannot fetch student courses");
        }
        return courseList;

    }


}
