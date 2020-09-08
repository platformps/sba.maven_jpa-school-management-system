package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
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
        this(DatabaseConnection.UAT);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        try {
            // TODO - Parse `List<StudentInterface>` from `resultSet`
            List<StudentInterface> students = new ArrayList<>();

            while (resultSet.next()){
                Student student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                students.add(student);
            }

            return students;
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> list = getAllStudents();

        for (StudentInterface studentInterface : list) {
            if (studentInterface.getEmail().equals(studentEmail)) {
                return studentInterface;
            }
        }

        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {

        return getAllStudents()
                .stream()
                .anyMatch(s -> s.getEmail().equals(studentEmail) && s.getPassword().equals(password));
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        CourseService courseService = new CourseService();
        CourseInterface courseToAdd = courseService.getAllCourses()
                .stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElse(null);


        dbc.executeStatement("insert into StudentCourse (email, id, name, instructor) values ('"
                + studentEmail + "','"
                + courseToAdd.getId() + "', '"
                + courseToAdd.getName() + "', '"
                + courseToAdd.getInstructor() + "')"
        );
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }
}
