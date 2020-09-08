package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            List<StudentInterface> studentList = new ArrayList<>();
            while(resultSet.next()){
                Student student = new Student();
                student.setEmail(resultSet.getString(1));
                student.setName(resultSet.getString(2));
                student.setPassword(resultSet.getString(3));
                studentList.add(student);
            }
            return studentList;
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> studentList = getAllStudents();
        for(StudentInterface studentInterface:studentList){
            if(studentInterface.getEmail().equals(studentEmail))
                return studentInterface;
        }
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        StudentInterface student = getStudentByEmail(studentEmail);
        if(student == null){
            return false;
        }
        return password.equals(student.getPassword());
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
