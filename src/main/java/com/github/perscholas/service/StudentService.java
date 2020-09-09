package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
            dbc.getDatabaseConnection().close();
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
        dbc.executeStatement("insert into StudentCourse (courseId, studentEmail) values (" +
                courseId+",'"+ studentEmail+"');");
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        ResultSet resultSet = dbc.executeQuery("SELECT Course.name, Course.instructor, Course.id FROM StudentCourse, Course WHERE StudentCourse.student_email='" + studentEmail +"' AND Course.id = StudentCourse.course_id"  );
        try {
            List<CourseInterface> result = new ArrayList<>();
            while(resultSet.next()) {
                Course course = new Course();
                course.setName(resultSet.getString(1));
                course.setInstructor(resultSet.getString(2));
                course.setId(Integer.parseInt(resultSet.getString(3)));
                result.add(course);
            }
            dbc.getDatabaseConnection().close();
            return result;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
