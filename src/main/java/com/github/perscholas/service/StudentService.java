package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    private final DatabaseConnection dbc;
    private static final String persistentName = "SMS";
    private static EntityManagerFactory factory;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    //    public StudentService() {
//        this(DatabaseConnection.UAT);
//    }
    public StudentService() {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student");
        try {
            List<StudentInterface> studentList = new ArrayList<>();
            while (resultSet.next()) {
                StudentInterface student = new Student();
                student.setEmail(resultSet.getString("email"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                studentList.add(student);
            }

            return studentList; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
     //   ResultSet resultSet = dbc.executeQuery("SELECT * FROM Student where email='"+studentEmail+"'");
        List<StudentInterface> list = getAllStudents();
        for (StudentInterface studentInterface : list) {
            if (studentInterface.getEmail().equals(studentEmail)) {
                //System.out.println(studentInterface);
                return studentInterface;
            }
        }
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        Boolean isValid= getAllStudents().stream()
                .anyMatch(students -> students.getEmail().equals(studentEmail) && students.getPassword().equals(password));
    return isValid;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
//        factory=Persistence.createEntityManagerFactory(persistentName);
//        EntityManager entityManager = factory.createEntityManager();
//        Query q = entityManager.createQuery();
//        List<StudentInterface> studentInterfaceList = q.getResultList();
//        System.out.println(studentInterfaceList);



        List<CourseInterface> courseList = new CourseService().getAllCourses();
        if (getStudentCourses(studentEmail).size() == 0) {
            Student student = (Student) getStudentByEmail(studentEmail);
            CourseInterface addCourse = courseList.stream()
                    .filter(courseInterface -> courseInterface.getId().equals(courseId))
                    .findFirst().get();

            student.getCourses().add(addCourse);
          //  System.out.println(student.getCourses());
        } else {
            for (CourseInterface course : getStudentCourses(studentEmail)) {
                if (course.getId() != courseId) {
                    Student student = (Student) getStudentByEmail(studentEmail);
                    CourseInterface addCourse = courseList.stream()
                            .filter(courseInterface -> courseInterface.getId().equals(courseId))
                            .findFirst().get();

                    student.getCourses().add(addCourse);
                } else
                    System.out.println("Already registered to course");
            }
        }


    }


    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {

        Student student = (Student) getStudentByEmail(studentEmail);
            List<CourseInterface> courseList = student.getCourses();
            return courseList;

    }


}
