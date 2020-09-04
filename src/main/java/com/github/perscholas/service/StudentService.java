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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM student");
        try {
            return null; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
      //  return null;
        return getAllStudents().stream()
                .filter(students -> students.getEmail().equals(studentEmail))
                .findFirst()
                .get();
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
       // return null;
        return getAllStudents().stream()
                .filter(students -> students.getEmail().equals(studentEmail) && students.getPassword().equals(password))
                .count() == 1 ? true : false;
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

        //return null;
        List<CourseInterface> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM Student_Course WHERE email = ?";
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

