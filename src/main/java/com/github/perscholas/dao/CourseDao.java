package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 5:56 PM
 */
public interface CourseDao {
    /**
     * reads the course table in database
     * @return database data as a List<Course>
     */
    List<CourseInterface> getAllCourses();
}
