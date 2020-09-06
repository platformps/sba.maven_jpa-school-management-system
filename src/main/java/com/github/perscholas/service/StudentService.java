package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.dao.StudentDaoJdbcImpl;
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

        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
<<<<<<< HEAD
       // ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
=======
>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM student");
        try {
            //return null; // TODO - Parse `List<StudentInterface>` from `resultSet`
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
<<<<<<< HEAD
        //return null;
        return getAllStudents().stream()
                .filter(std -> std.getEmail().equals(studentEmail))
=======
      //  return null;
        return getAllStudents().stream()
                .filter(students -> students.getEmail().equals(studentEmail))
>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
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
<<<<<<< HEAD

        List<CourseInterface> studentCourses = getStudentCourses(studentEmail);
        Optional<CourseInterface> course = studentCourses
                 .stream()
                 .filter(temp -> temp.getId() == courseId)
                 .findFirst();
        if(course.isPresent()) {
            course.ifPresent(temp -> System.out.println("Already registered to this Course!!"));
            return;
        }
        String query = "INSERT INTO StudentCourse values (?, ?)";
=======
        List<CourseInterface> studentCourses = getStudentCourses(studentEmail);
        Optional<CourseInterface> course = studentCourses.stream()
                .filter(crs -> crs.getId() == courseId)
                .findFirst();
        if(course.isPresent()) {
            course.ifPresent(crs -> System.out.println("Already registered to this Course!!"));
            return;
        }
        String query = "INSERT INTO Student_Course values (?, ?)";
>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
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
<<<<<<< HEAD
       // return null;
        List<CourseInterface> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM management_system.StudentCourse WHERE Student_email= ?";
=======

        //return null;
        List<CourseInterface> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM Student_Course WHERE email = ?";
>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
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
<<<<<<< HEAD
    }
}
=======
    }
    }

>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
