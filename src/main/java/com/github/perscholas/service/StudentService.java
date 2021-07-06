package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.*;

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
            List<StudentInterface> result = new ArrayList<>();
            while(true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new StudentBuilder()
                        .withEmail(resultSet.getString("email"))
                        .withName(resultSet.getString("name"))
                        .withPassword(resultSet.getString("password"))
                        .build());
            }
            resultSet.close();
            dbc.getDatabaseConnection().close();
            return result;
        } catch(Exception e) {
            throw new Error(e);
        }

    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> students = getAllStudents();
        for(StudentInterface student: students ){
            if(student.getEmail().equals(studentEmail))
                return student;
        }

        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password)  {
        StudentInterface student = getStudentByEmail(studentEmail);
        if (student == null)
            return false;
        ;
        return (student.getPassword().equals(password));
    }

    @Override
    public void registerStudentToCourse(String studentEmail, Integer courseId) {
            dbc.executeStatement("insert into StudentCourse (course_id, student_email) values (" +
                                    courseId+",'"+ studentEmail+"');");
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        ResultSet resultSet = dbc.executeQuery("SELECT Course.name, Course.instructor, Course.id FROM StudentCourse, Course WHERE StudentCourse.student_email =" + "'" +studentEmail +"' AND Course.id = StudentCourse.course_id"  );
        try {
            List<CourseInterface> result = new ArrayList<>();
            while(true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new CourseBuilder()
                        .withName(resultSet.getString("name"))
                        .withId(resultSet.getInt("id"))
                        .withInstructor(resultSet.getString("instructor"))
                        .build());
            }
            resultSet.close();
            dbc.getDatabaseConnection().close();
            return result;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
