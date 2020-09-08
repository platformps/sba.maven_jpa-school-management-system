package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


// TODO - Implement respective DAO interface

/**
 * TODO implemented by Monica Deshmukh
 * 9/6/2020
 */


public class StudentService implements StudentDao {
    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    //changed for testing purpose..revert the comment beofore submitting************************************
   /* public StudentService() {
        this(DatabaseConnection.UAT);
    }*/
    public StudentService() {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    /**
     * reads the student table in database
     * @return database data as a List<Student>
     */
    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
             // TODO - Parse `List<StudentInterface>` from `resultSet`
            List<StudentInterface> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("password")));
            }
            return students;
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        try{
            return getAllStudents()
                    .stream()
                    .filter(student -> student.getEmail().equals(studentEmail))
                    .findFirst()
                    .get();
        }catch(NullPointerException| NoSuchElementException e){
            System.out.println("Email address does not exist. Please enter valid email.");
            return null;
        }
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
       /*Boolean studentExists = getAllStudents()
                                .stream()
                                .anyMatch(student -> student.getEmail().equals(studentEmail) && student.getPassword().equals(password));
       if (!studentExists)
            System.out.println("No entry found for this student. Please enter valid email and password.");
        return studentExists;*/
        //Leon's approach
        try {
            Boolean studentExists =  password.equals(Objects.requireNonNull(getStudentByEmail(studentEmail)).getPassword());
            if (!studentExists)
                System.out.println("No entry found for this student. Please enter valid email and password.");
            return studentExists;
        }catch (NullPointerException e){
            System.out.println("No entry found for this student. Please enter valid email and password.");
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        List<CourseInterface> registeredCourses = getStudentCourses(studentEmail);

        //If the user selects an invalid course ID give error message
        CourseService courseService = new CourseService();
        List<CourseInterface> courses = courseService.getAllCourses();
        if (!courses.stream().anyMatch(course -> course.getId().equals(courseId)))
            System.out.println("This is not a valid courseId. Please choose a valid course from the given list.");
        else {
            if (registeredCourses != null && registeredCourses.stream().anyMatch(thisCourse -> thisCourse.getId().equals(courseId))) {
                System.out.println("The Student is already registered for this course.");
            }
            else {
                registerForCourse(studentEmail, courseId);

                //display registered courses after successfully registering to the course
                registeredCourses =  getStudentCourses(studentEmail);
                System.out.println("[ " + studentEmail + " ] is registered for the following courses: " + registeredCourses.toString());
            }
        }
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> courses = new ArrayList<>();

        try{
          String statement = "SELECT c.id, c.name, c.instructor " +
                                "FROM Course c, StudentCourses sc " +
                                "WHERE sc.studentEmail = '" + studentEmail +"' " +
                                "AND sc.courseId = c.id";
            ResultSet resultSet = dbc.executeQuery(statement);
            while (resultSet.next()) {
                    courses.add(new Course(resultSet.getInt("c.id"),
                            resultSet.getString("c.name"),
                            resultSet.getString("c.instructor")));
                }
                return courses;
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
    }

    private void registerForCourse(String studentEmail, int courseId) {
        try{
            String registerCourse = "INSERT INTO StudentCourses VALUES (?, ?)";
            PreparedStatement insertStatement = dbc.getDatabaseConnection().prepareStatement(registerCourse);
            insertStatement.setString(1, studentEmail);
            insertStatement.setInt(2, courseId);
            insertStatement.execute();
        }catch(SQLException e){
            System.out.println("Unable to register for this course.");
            e.printStackTrace();
        }
    }
}
