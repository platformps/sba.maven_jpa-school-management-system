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
public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public CourseService() {
        this(DatabaseConnection.UAT);
    }

    @Override
    public List<CourseInterface> getAllCourses() {
        List<CourseInterface> courses = new ArrayList<>();
        ResultSet rs = dbc.executeQuery("SELECT * FROM Course;");
        try {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("instructor")));
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return courses;
    }
}
