package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.mysql.cj.protocol.Resultset;

import java.sql.DriverManager;
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
        ResultSet rs = dbc.executeQuery("SELECT * FROM Student");
        StudentInterface student = new Student();
        try {
            List<StudentInterface> studentInterfaces = new ArrayList<>();
            while(rs != null && rs.next()) {
                student.setEmail(rs.getString("email"));
                student.setName(rs.getString("name"));
                student.setPassword(rs.getString("password"));
                studentInterfaces.add(student);
            }
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
            return studentInterfaces;// TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        StudentInterface studentInterface = new Student();
        try{
            PreparedStatement ps = dbc.getDatabaseConnection()
                    .prepareStatement("SELECT * FROM Student WHERE email = ?");
            ps.setString(1, studentEmail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentInterface.setEmail(rs.getString("email"));
                studentInterface.setName(rs.getString("name"));
                studentInterface.setPassword(rs.getString("password"));
            }
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return studentInterface;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        try{
            ResultSet rs = dbc.executeQuery("SELECT * FROM Student");
            while (rs != null && rs.next()) {
                if(studentEmail.equals(rs.getString("email"))) {
                    if(password.equals(rs.getString("password"))) {
                        return true;
                    }
                }
            }
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        String sql = "INSERT INTO intermediate (student_email, course_id) VALUES (" + studentEmail + ", " + courseId + ")";
        dbc.executeStatement(sql);
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        CourseInterface courseInterface = new Course();
        List<CourseInterface> courseInterfaceList = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM Course c JOIN intermediate i " +
                "WHERE i.student_email = " + studentEmail;
        ResultSet rs = dbc.executeQuery(sql);
        try {
            while (rs != null && rs.next()) {
                courseInterface.setId(rs.getInt("id"));
                courseInterface.setName(rs.getString("name"));
                courseInterface.setInstructor(rs.getString("instructor"));
                courseInterfaceList.add(courseInterface);
            }
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return courseInterfaceList;
    }
}
