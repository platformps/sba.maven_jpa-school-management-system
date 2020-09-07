package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
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
             System.out.println("The value of dbc"+dbc);
             resultSet = dbc.executeQuery("SELECT email,name,password FROM students");
            if (resultSet != null) {
                listStudent = new ArrayList<StudentInterface>();
                while(resultSet.next()){
                String email= resultSet.getString(1);
                String name= resultSet.getString(2);
                String password= resultSet.getString(3);
                StudentInterface student = new Student(email,name,password);
                System.out.println("Before student to List" + student.toString());
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
            resultSet = dbc.executeQuery("SELECT name,password FROM students where studentEmail='studentEmail'");
            if (resultSet != null) {
                studentInterface = new Student();
             while(resultSet.next()){
                    studentInterface.setName(studentEmail);
                    studentInterface.setName(resultSet.getString(1));
                    studentInterface.setPassword(resultSet.getString(2));
                    System.out.println("Student details based on provided emailID" + studentInterface.toString());
                }
            }
             // TODO - Parse `List<StudentInterface>` from `resultSet`
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
            System.out.println("The value of dbc in validateStudent "+dbc.getDatabaseConnection());
            resultSet = dbc.executeQuery(
                    "SELECT name FROM student where email='"+studentEmail+"' and password='"+password+"'");
            System.out.println(resultSet);
            if(resultSet!=null){
                isStudent =true;

            }
            System.out.println("The value of result and boolean value "+isStudent);
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

    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
