package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
@Service
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
            // TODO - Parse `List<StudentInterface>` from `resultSet`
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
            System.out.println(e);
        }
        return null;
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
