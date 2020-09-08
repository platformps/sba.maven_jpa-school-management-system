package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentBuilder;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    private final Student student;
    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
        this.student = new StudentBuilder().createStudent();
    }

    public StudentService() {
        this(DatabaseConnection.UAT);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        try {

            List<Student> listStudent;

            return null; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        return null;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
