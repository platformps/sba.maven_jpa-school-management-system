package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM student");
        try {
            List<StudentInterface> students = new ArrayList<>();
            while(resultSet.next()) {
                Student student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                students.add(student);
            }
            return students;
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        String query = "SELECT * FROM student WHERE email=?";
        try {
            PreparedStatement preparedStatement = dbc.getDatabaseConnection().prepareStatement(query);
            preparedStatement.setString(1, studentEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Student student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        StudentInterface student = getStudentByEmail(studentEmail);
        if(student == null) return false;
        if(student.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        List<CourseInterface> studentCourses = getStudentCourses(studentEmail);
        Optional<CourseInterface> course = studentCourses.stream()
                .filter(crs -> crs.getId() == courseId)
                .findFirst();
        if(course.isPresent()) {
            course.ifPresent(crs -> System.out.println("Already registered to this Course!!"));
            return;
        }
        String query = "INSERT INTO Student_Course values (?, ?)";
        try {
            PreparedStatement preparedStatement = dbc.getDatabaseConnection().prepareStatement(query);
            preparedStatement.setString(1, studentEmail);
            preparedStatement.setInt(2, courseId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM Student_Course WHERE Student_email=?";
        try {
            PreparedStatement preparedStatement = dbc.getDatabaseConnection().prepareStatement(query);
            preparedStatement.setString(1, studentEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int courseId = resultSet.getInt("Course_id");
                studentCourses.add(new CourseService().getAllCourses()
                        .stream()
                        .filter(crs -> crs.getId() == courseId)
                        .findFirst()
                        .get());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentCourses;
    }
}
