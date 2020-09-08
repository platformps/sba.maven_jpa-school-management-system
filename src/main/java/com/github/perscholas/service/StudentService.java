package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import org.w3c.dom.ls.LSOutput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
         ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        List<StudentInterface> studentList = new ArrayList<>();
            try {  while (resultSet.next()){
            Student student = new Student();
            student.setEmail(resultSet.getString("email"));
            student.setName(resultSet.getString("name"));
            student.setPassword(resultSet.getString("password"));

            studentList.add(student);//put result set into a list. then store into List<StudentInterface>?

        }
             // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);

        }  return studentList;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
     List<StudentInterface> studentByEmail = getAllStudents().stream().filter( student -> student.getEmail().equals(studentEmail))
             .collect(Collectors.toList());

        return (StudentInterface) studentByEmail;
    }


    @Override
    public Boolean validateStudent(String studentEmail, String password) {
    StudentInterface studentToValidate = getStudentByEmail(studentEmail);

        if (studentToValidate.getPassword().equals(password)) {

            return true;
        }else return false;

    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        //statement
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
    // read - query
}
