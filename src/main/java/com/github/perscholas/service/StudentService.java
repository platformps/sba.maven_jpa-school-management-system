package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<StudentInterface> listStudent = new ArrayList<>();
        try {
            while (resultSet.next()){
                listStudent.add(new StudentBuilder()
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                 .setName(resultSet.getString("name"))
                    .createStudent());
            }


            return listStudent; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> students = getAllStudents();
        for (StudentInterface studentInterface : students) {
            if (studentInterface.getEmail().equals(studentEmail)){
                return studentInterface;
            }
        }
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        StudentInterface students = getStudentByEmail(studentEmail);
        if (students == null) {
            return null;
        }else {
            return students.getPassword().equals(password);
        }
    }

    @Override
    public void registerStudentToCourse(String studentEmail, Integer courseId) {
        dbc.executeStatement("insert into student(email, courseId) Values(" +
                studentEmail +"," + courseId+ ")");

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> listCourse = new ArrayList<>();
        ResultSet resultSet = dbc.executeQuery("Select course.id, course.name, course.instructor" +
                " where student.email = " + studentEmail + " and course.id = student.courseId");
        try{
            while (resultSet.next()){
                listCourse.add(new CourseBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setInstructor(resultSet.getString("instructor"))
                .createCourse());
            }
            return listCourse;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }
