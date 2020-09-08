package com.github.perscholas.service;


import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {

    private final DatabaseConnection dbc;


    public CourseService() {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    @Override
    public List<CourseInterface> getAllCourses() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Course");
        try {
            List<CourseInterface> allCourses = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String instructor = resultSet.getString("instructor");
                CourseInterface course = new Course(id, name, instructor);
                allCourses.add(course);
            }
            return allCourses;
        } catch(Exception e) {
            throw new Error(e);
        }

    }
}
