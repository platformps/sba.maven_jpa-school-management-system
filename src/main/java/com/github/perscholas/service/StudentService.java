package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentService implements StudentDao {
    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentService() {
        this(DatabaseConnection.UAT);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        List<StudentInterface> studentList = new ArrayList<>();
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student;");

        try {
            do {
                studentList.add(new Student(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("password")));

            } while (Objects.requireNonNull(resultSet).next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return getAllStudents().stream()
                .filter(studentInterface -> studentInterface.getEmail().equals(studentEmail))
                .findAny()
                .get();
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        return password.equals(Objects.requireNonNull(getStudentByEmail(studentEmail)).getPassword());
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        dbc.executeStatement("insert into StudentCourse(studentEmail, courseId) values (" + studentEmail + ", " + courseId + ");");
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> list = new ArrayList<>();
        ResultSet resultSet = dbc.executeQuery("SELECT c.id, c.name, c.instructor FROM course c, intermediate i WHERE i.student_email = '" + studentEmail + "' AND i.course_id = c.id;");

        try {
            do {
                list.add(new Course(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("instructor")) {
                });

            } while (Objects.requireNonNull(resultSet).next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
