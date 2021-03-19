package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.CourseBuilder;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {

    DatabaseConnection dc;
    public CourseService() {
        this.dc = dc;
    }


    @Override
    public List<CourseInterface> getAllCourses() {
        String sqlQuery = "select * form course";
        ResultSet result = dc.executeQuery(sqlQuery);
        List<CourseInterface> courses = new ArrayList<>();
        try {
            while (result.next()) {
                courses.add(new CourseBuilder()
                        .setId(result.getInt("id"))
                        .setName(result.getString("name"))
                        .setInstructor(result.getString("instructor"))
                        .createCourse());
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return courses;
    }
}
