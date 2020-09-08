package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM Course");
        try {
            List<CourseInterface> courseList = new ArrayList<>();
            while(resultSet.next()){
                Course course = new Course();
                course.setId(Integer.parseInt(resultSet.getString(1)));
                course.setName(resultSet.getString(2));
                course.setInstructor(resultSet.getString(3));
                courseList.add(course);
            }
            return courseList;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
