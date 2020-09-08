package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<CourseInterface> courseList = new ArrayList<>();
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Course;");

        try {
            do {
                courseList.add(new Course(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("instructor")));

            } while (Objects.requireNonNull(resultSet).next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
}
