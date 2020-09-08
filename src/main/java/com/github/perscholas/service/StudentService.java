package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            //GN implemented while loop
            List<StudentInterface> studentList = new ArrayList<StudentInterface>();
            while(resultSet.next()) {
                StudentInterface student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                studentList.add(student);
            }
            return studentList; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        //GN added body of method
        ResultSet resultSet = dbc.executeQuery
                                ("SELECT * FROM Student WHERE email = \'" +
                                          studentEmail + "\'");
        try {
            //TODO check for corner cases

            if(resultSet.next()) {
                StudentInterface student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));

                return student; // TODO - Parse `List<StudentInterface>` from `resultSet`
            }
            else {
                return null;
            }

        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        String query = "SELECT * FROM Student WHERE email = \'" +
                studentEmail + "\'" +
                "AND password = \'" +
                password + "\';";
        ResultSet resultSet = dbc.executeQuery(query);
        boolean isValidStudent = false;
        try {
            //TODO check for corner cases
            isValidStudent = resultSet.next();

            if(!isValidStudent) {
                throw new Exception("Student was not found. Please try again with a different email and/or password.");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return isValidStudent;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

        String query = "INSERT INTO StudentCourse (studentEmail, courseId) values (" +
                "\'" + studentEmail + "\'," +
                "\'" + courseId + "\');";
            dbc.executeStatement(query);
            //throw new SQLIntegrityConstraintViolationException("Student " + studentEmail + " is already registered for course with id = " +courseId);

            //System.out.println("Student " + studentEmail + " is already registered for course with id = " +courseId);
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {

        String sqlQuery =
                "SELECT c.id, c.name, c.instructor " +
                "FROM Course c JOIN StudentCourse sc " +
                "ON c.id = sc.courseId AND sc.studentEmail = " +
                "\'" + studentEmail + "\';";


        ResultSet resultSet = dbc.executeQuery(sqlQuery);
        try {
            //GN implemented while loop
            List<CourseInterface> courseList = new ArrayList<CourseInterface>();
            while(resultSet.next()) {
                CourseInterface course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setInstructor(resultSet.getString("instructor"));
                courseList.add(course);
            }
            return courseList;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
