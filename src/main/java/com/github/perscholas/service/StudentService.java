package com.github.perscholas.service;
import com.github.perscholas.model.*;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityContraintViolationException;
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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        try {
            List<StudentInterface> studentList = new ArrayList<StudentInterface>();
            while(resultSet.next()){
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
        ResultSet resultSet = dbc.executeQuery
                ("SELECT * FROM Student WHERE email = \'" + studentEmail + "\'");
        try{
            resultSet.next();
            StudentInterface student = new Student();
            student.setEmail(resultSet.getString("email"));
            student.setName(resultSet.getString("name"));
            student.setPassword(resultSet.getString("password"));

            return student;
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
        try {
            return resultSet.next();
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
    String query = "INSERT INTO StudentCourse (studentEmail, courseID) values (" +
            "\'" + studentEmail + "\'," +
            "\'" + courseId + "\');";
    dbc.executeStatement(query);
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
