package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.RepositoryInterface;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService  implements StudentDao {
    private final DatabaseConnection dbc;
    List<StudentInterface> list=new ArrayList<>();

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
            while(resultSet.next()){
            Student student = new Student();
            student.setEmail(resultSet.getString("email"));
            student.setName(resultSet.getString("name"));
            student.setPassword(resultSet.getString("password"));
           list.add(student);
        }
            return list; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        StudentInterface student = list.stream()
                .filter(e-> studentEmail.equals(e.getEmail()))
                .findAny()
                .orElse(null)
                ;
        return student;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        Student student= (Student) getStudentByEmail(studentEmail);
        if (list.stream().anyMatch(x -> x.getPassword().equals(password))){
          return true; }

        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {

    }


    @Override public List<CourseInterface> getStudentCourses(String studentEmail) {

  StudentInterface student =  list.stream()
                .filter(e ->studentEmail.equals(e.getEmail()))
                .findFirst()
                .orElse(null);
       if(null==student.getClasses()) return null;

        return student.getClasses();
    }
}



