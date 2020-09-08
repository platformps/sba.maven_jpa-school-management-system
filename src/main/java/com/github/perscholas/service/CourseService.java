package com.github.perscholas.service;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.DatabaseConnection;
import java.sql.*;
import java.util.*;


// TODO - Implement respective DAO interface
public class CourseService implements CourseDao{

        private final DatabaseConnection dbc;

        public CourseService(DatabaseConnection dbc) {
            this.dbc = dbc;
        }

        public CourseService() {
            this(DatabaseConnection.MANAGEMENT_SYSTEM);
        }

        @Override
        public List<CourseInterface> getAllCourses() {
            List<CourseInterface> coursesList = new ArrayList<>();
            try {
                String query = "SELECT * FROM course";
                ResultSet resultSet = dbc.executeQuery(query);

                while(resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getInt("id"));
                    course.setName(resultSet.getString("name"));
                    course.setInstructor(resultSet.getString("instructor"));
                    coursesList.add(course);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return coursesList;
        }
}
