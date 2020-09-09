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

        List<CourseInterface> studentList=getStudentCourses(studentEmail);
        Optional<CourseInterface> registeredCourses=studentList
                .stream()
                .filter(x -> x.getId().equals(courseId))
                .findFirst();
        if (registeredCourses.isPresent())
        {
            System.out.println("You are already registered for this course");
        }
        else
        {
            String studentCourseQuery ="INSERT INTO Student_Course"
                    +"values(?,?)";
            try {
                PreparedStatement preparedStatement=dbc.getDatabaseConnection().prepareStatement(studentCourseQuery);
                preparedStatement.setString(1,studentEmail);
                preparedStatement.setInt(2,courseId);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        //This method takes a Student’s Email as a parameter and would find all the courses a student is registered.
        //step 1. match the email using getStudent by email.
        //step 2. searches something like a list or a table ???
        //step 3. return a list that gets all the courses using the course object in course class.
        // tables Student and course  are needed.

        List<CourseInterface>courseInterfaceList=new ArrayList<>();
        String getStudentCourseQuery="Select * FROM Student_Course WHERE email=?"+";";

        try {
            PreparedStatement preparedStatement=dbc.getDatabaseConnection()
                    .prepareStatement(getStudentCourseQuery);
            preparedStatement.setString(1,studentEmail);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int courseId=resultSet.getInt("Course_id");
                courseInterfaceList.add(new CourseService().getAllCourses()
                        .stream()
                        .filter(x ->x.getId().equals(courseId))
                        .collect(Collectors.toList()).get(0)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
         }
        return  courseInterfaceList;

    }
}
