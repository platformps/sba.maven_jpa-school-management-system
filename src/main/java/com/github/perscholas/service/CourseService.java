package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.*;

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
            List<CourseInterface> result = new ArrayList<>();
            while(true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new CourseBuilder()
                        .withId(Integer.parseInt(resultSet.getString("id")))
                        .withName(resultSet.getString("name"))
                        .withInstructor(resultSet.getString("instructor"))
                        .build());
            }
            dbc.getDatabaseConnection().close();
            return result;
        } catch(Exception e) {
            throw new Error(e);
        }
    }

    public CourseInterface getCourseById(Integer id) {
        List<CourseInterface> courses = getAllCourses();
        for(CourseInterface course: courses ){
            if(course.getId().equals(id))
                return course;
        }

        return null;
    }


    public List<StudentInterface> getCourseStudents(String courseId) {
        ResultSet resultSet = dbc.executeQuery("SELECT Student.name FROM StudentCourse, Student WHERE StudentCourse.course_id =" + "'" +courseId +"'");
        try {
            List<StudentInterface> result = new ArrayList<>();
            while(true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new StudentBuilder()
                        .withName(resultSet.getString("name"))
                        .build());
            }
            dbc.getDatabaseConnection().close();
            return result;
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
