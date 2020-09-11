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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            // TODO - Parse `List<StudentInterface>` from `resultSet`
            List<StudentInterface> students = new ArrayList<>();

            while (resultSet.next()){
                System.out.println(resultSet.getString("email"));
                Student student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
               // System.out.println(student.toString());

                students.add(student);
            }
           // students.stream().forEach(e -> System.out.println(e.toString()));
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
        List<StudentInterface> list = getAllStudents();
       // list.stream().forEach(e -> System.out.println(e.toString()));

        return list
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
        List<CourseInterface> listCourseByEmail = new ArrayList<>();
        ResultSet result = dbc.executeQuery("SELECT c.id, name, instructor " +
                    "FROM course c, StudentCourses sc " +
                    "WHERE sc.courseId = c.id" +
                    "AND sc.studentEmail = '" + studentEmail + "'");

        try {
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String instructor = result.getString("instructor");
                CourseInterface course = new Course(id, name, instructor);
                listCourseByEmail.add(course);
            }
            return listCourseByEmail;

        } catch (SQLException se) {
            throw new Error(se);
        }
    }
}
