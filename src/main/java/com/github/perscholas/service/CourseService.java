package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Implement Course DAO interface
public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;

    // constructor takes dbc
    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public CourseService() {
        this(DatabaseConnection.UAT);
    }

    @Override
    public List<CourseInterface> getAllCourses() {
        ResultSet rs = dbc.executeQuery("SELECT * FROM courses");
        try {
            //Parse `List<CourseInterface>` from `resultSet`
            List<CourseInterface> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("instructor")));
            }
            return courses; // returns list
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}


