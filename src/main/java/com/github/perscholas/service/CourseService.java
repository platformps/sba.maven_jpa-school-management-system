package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.github.perscholas.DatabaseConnection;

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

        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        List<CourseInterface> courseList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setInstructor(resultSet.getString("instructor"));

                courseList.add(course);
                System.out.println(courseList);
            }
            return courseList;
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

