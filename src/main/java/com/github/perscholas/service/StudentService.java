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
        ResultSet resultSet = null;
        List<StudentInterface> listStudent = null;
        try {
            resultSet = dbc.executeQuery("SELECT email,name,password FROM management_system.student");
            if (resultSet != null) {
                listStudent = new ArrayList<StudentInterface>();
                while(resultSet.next()){
                String email= resultSet.getString(1);
                String name= resultSet.getString(2);
                String password= resultSet.getString(3);
                StudentInterface student = new Student(email,name,password);
                listStudent.add(student);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        } finally{
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return listStudent;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        ResultSet resultSet =null;
        StudentInterface studentInterface = null;
        try {
            resultSet = dbc.executeQuery("SELECT name,password FROM management_system.student where email='"+studentEmail+"'");
            if (resultSet != null) {
                studentInterface = new Student();
             while(resultSet.next()){
                    studentInterface.setName(studentEmail);
                    studentInterface.setName(resultSet.getString(1));
                    studentInterface.setPassword(resultSet.getString(2));
                }
            }
         } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
        finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return studentInterface;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        Boolean isStudent = false;
        ResultSet resultSet = null;

        try{
            resultSet = dbc.executeQuery(
                    "SELECT name FROM management_system.student " +
                            "where email='"+studentEmail+"' and password='"+password+"'");
            if(resultSet!=null&&resultSet.isBeforeFirst()){
                isStudent =true;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Error(e);
        }finally{
            try{
                if(resultSet!=null){
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return isStudent;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        try{
            String sql="insert into management_system.student_course (email, id) values ('"+studentEmail+"','"+courseId+"')";
            dbc.executeStatement(sql);
           }catch(Exception e){
            e.printStackTrace();
            throw new Error(e);
        }finally{
        }
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        ResultSet resultSet = null;
        List<CourseInterface> listStudentCourse = null;
        try {
            resultSet = dbc.executeQuery("SELECT id FROM management_system.student_course where email='"+studentEmail+"'");
            if (resultSet != null) {
                listStudentCourse = new ArrayList<CourseInterface>();
                while(resultSet.next()){
                    Course course= getCourseDetailsByID(resultSet.getInt(1));
                    listStudentCourse.add(course);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        } finally{
            try{
                if(resultSet!=null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return listStudentCourse;
    }

    private Course getCourseDetailsByID(int courseID){
        ResultSet courseResultSet =null;
        Course course = null;
        try {
              courseResultSet = dbc.executeQuery(
                    "SELECT id,name,instructor FROM management_system.course where id='" + courseID + "'");
            if (courseResultSet != null) {
                //only one record exist as this is primary key
                while(courseResultSet.next()){
                    course= new Course(courseResultSet.getInt(1),
                            courseResultSet.getString(2),courseResultSet.getString(3));

                }
            }
          }catch(Exception e){
            e.printStackTrace();
          }finally {
            try{
                if(courseResultSet!=null) {
                    courseResultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return course;
    }
}
