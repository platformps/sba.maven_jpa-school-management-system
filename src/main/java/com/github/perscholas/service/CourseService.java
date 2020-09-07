package com.github.perscholas.service;

// TODO - Implement respective DAO interface

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO implemneted by Monica Deshmukh
 * 9/6/2020
 */

public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;

    public CourseService(DatabaseConnection dbc) {
            this.dbc = dbc;
        }

    //changed for testing purpose..revert the comment beofore submitting************************************
   /* public StudentService() {
        this(DatabaseConnection.UAT);
    }*/
    public CourseService() {
            this(DatabaseConnection.MANAGEMENT_SYSTEM);
        }
    @Override
    public List<CourseInterface> getAllCourses() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Course");
        try {
            List<CourseInterface> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("instructor")));
            }
            return courses;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
