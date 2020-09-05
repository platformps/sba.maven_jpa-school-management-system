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
        ResultSet rs = dbc.executeQuery("SELECT * FROM Course");
        CourseInterface courseInterface = new Course();
        try {
            while(rs != null && rs.next()) {
                courseInterface.setId(rs.getInt("id"));
                courseInterface.setName(rs.getString("name"));
                courseInterface.setInstructor(rs.getString("instructor"));
                courses.add(courseInterface);
            }
            if(rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
            return courses;// TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }
}
