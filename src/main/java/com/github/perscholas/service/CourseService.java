package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;


    //
    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }
    public CourseService()
    {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }




    @Override
    public List<CourseInterface> getAllCourses() {
        List<CourseInterface> courseInterfaceList= new ArrayList<>();
        String CourseQuery= "SELECT * FROM course";

        ResultSet resultSet= dbc.executeQuery(CourseQuery);
            try {
                while (resultSet.next())
                {
                    Course course= new Course();
                    course.setId(resultSet.getInt("id"));
                    course.setName(resultSet.getString("name"));
                    course.setInstructor(resultSet.getString("Instructor"));
                    courseInterfaceList.add(course);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return courseInterfaceList;

    }
}