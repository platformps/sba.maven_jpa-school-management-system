package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;

import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 5:56 PM
 */
public interface CourseDao {
    List<CourseInterface> getAllCourses();
}
