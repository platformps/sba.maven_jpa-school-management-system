package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
<<<<<<< HEAD
public class CourseService implements CourseDao  {
    private DatabaseConnection dbc;
=======

public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;
>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public CourseService() {
<<<<<<< HEAD
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
=======
        this(DatabaseConnection.UAT);
    }

>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
    public List<CourseInterface> getAllCourses() {
        List<CourseInterface> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM course";
            ResultSet resultSet = dbc.executeQuery(query);

            while(resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setInstructor(resultSet.getString("instructor"));
                courses.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }
<<<<<<< HEAD
}
=======
    }

>>>>>>> d1ff7b9599907e7695a218c180940c96da835eb7
