package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO - Implement respective DAO interface
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
        return getAllStudents().stream()
                .anyMatch(studentInterface ->
                        studentInterface.getEmail().equals(studentEmail)
                        &&
                        studentInterface.getPassword().equals(password));
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
