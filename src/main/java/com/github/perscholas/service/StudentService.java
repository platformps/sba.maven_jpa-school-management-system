package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

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
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet rs = dbc.executeQuery("SELECT * FROM Student");
        List<StudentInterface> studentInterfaces = new ArrayList<>();
        try {
            while(rs != null && rs.next()) {
                studentInterfaces.add(new Student(rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password")));
            }
        } catch(Exception e) {
            throw new Error(e);
        } finally {
            closeResultSet(rs);
        }
        return studentInterfaces;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        ResultSet rs = dbc.executeQuery("SELECT * FROM Student WHERE email = " + studentEmail + ";");
        StudentInterface studentInterface = new Student();
        try{
            if (rs.next()) {
                studentInterface = new Student(rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new Error(e);
        } finally {
            closeResultSet(rs);
        }
        return studentInterface;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        ResultSet rs = dbc.executeQuery("SELECT * FROM student;");
        try{
            while (rs != null && rs.next()) {
                if(studentEmail.equals(rs.getString("email"))) {
                    if(password.equals(rs.getString("password"))) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        } finally {
            closeResultSet(rs);
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        if(!existingEntry(studentEmail, courseId)) {
            String sql = "INSERT INTO management_system.intermediate (student_email, course_id) VALUES ('" + studentEmail + "', '" + courseId + "');";
            dbc.executeStatement(sql);
        }
        else {
            System.out.println(studentEmail + " already registered to selected course: " + courseId);
        }
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        List<CourseInterface> courseInterfaceList = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.instructor FROM course c, intermediate i WHERE i.student_email = '" + studentEmail + "' AND i.course_id = c.id;";
        ResultSet rs = dbc.executeQuery(sql);
        try {
            while (rs != null && rs.next()) {
                courseInterfaceList.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructor")));
            }
        } catch (SQLException e) {
            throw new Error(e);
        } finally {
            closeResultSet(rs);
        }
        return courseInterfaceList;
    }

    public void closeResultSet(ResultSet rs) {
        try{
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    private Boolean existingEntry(String studentEmail, int courseId) {
        ResultSet rs = dbc.executeQuery("SELECT * FROM intermediate;");
        try{
            while (rs != null && rs.next()) {
                if(studentEmail.equals(rs.getString("student_email"))) {
                    if(courseId == rs.getInt("course_id")) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Error(e);
        } finally {
            closeResultSet(rs);
        }
        return false;
    }
}
