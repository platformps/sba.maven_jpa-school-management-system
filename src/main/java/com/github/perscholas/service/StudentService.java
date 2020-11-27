package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.PreparedStatement;
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

        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM student");
        List<StudentInterface>studentsList=new ArrayList<>();
        try {
            while(resultSet.next()) {
                StudentInterface student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        } finally{
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return studentsList;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return getAllStudents().stream()
                .filter(std -> std.getEmail().equals(studentEmail))
                .findFirst()
                .get();
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {

        return getAllStudents().stream()
                .filter(std -> std.getEmail().equals(studentEmail) && std.getPassword().equals(password))
                .count() >= 1;
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
        String query = "INSERT INTO student_course (student_email, course_id) values (?, ?)";
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
        String query = "SELECT c.* FROM student_course sc LEFT JOIN course c ON sc.course_id = c.id WHERE student_email=?";
        try {
            PreparedStatement preparedStatement = dbc.getDatabaseConnection().prepareStatement(query);
            preparedStatement.setString(1, studentEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int courseId = resultSet.getInt("id");
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
