package com.github.perscholas.dao;

import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//public class CourseDaoJdbcImpl implements CourseDao{
//    private JdbcTemplate jdbcTemplate;
//
//
//    private static final String SELECT_ALL_COURSES_SQL =
//            "select * from course";
//    @Autowired
//    public CourseDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Override
//    public List<CourseInterface> getAllCourses() {
//        return jdbcTemplate.query(
//                SELECT_ALL_COURSES_SQL,
//                this::mapRowToCourse);
//    }
//
//
//    private Course mapRowToCourse(ResultSet rs, int rowNum) throws SQLException {
//        Course course = new Course();
//        course.setId(rs.getInt("id"));
//        course.setName(rs.getString("name"));
//        course.setInstructor(rs.getString("instructor"));
//
//        return course;
//    }
//}
