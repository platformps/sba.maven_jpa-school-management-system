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
import java.util.Optional;
import java.util.stream.Collectors;

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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");

        List<StudentInterface> studentInterfaces= new ArrayList<>();

        try {
            while(resultSet.next())
            {
                Student students=new Student();
                students.setEmail(resultSet.getString("email"));
                students.setName(resultSet.getString("name"));
                students.setPassword(resultSet.getString("password"));

                studentInterfaces.add(students);
            }
        } catch(Exception e) {
            throw new Error(e);
        }
        return studentInterfaces; // TODO - Parse `List<StudentInterface>` from `resultSet`
    }


    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return  getAllStudents()
                .stream()
                .filter(students ->students.getEmail().equals(studentEmail))
                .collect(Collectors.toList()).get(0);

    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {

        if(getStudentByEmail(studentEmail).getEmail().equals(studentEmail))
        {
            if (getStudentByEmail(studentEmail).getPassword().equals(password))
            {
                return  true;
            }
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        //ResultSet resultSet = dbc.executeQuery("SELECT * FROM Students LEFT JOIN Course ON Students.email=Courses.id");
//        Student student = new Student();
//        student.getCourseList().get(0).getId()
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;

    }
}
