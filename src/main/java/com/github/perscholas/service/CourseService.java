package com.github.perscholas.service;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.CourseRepository;
import com.github.perscholas.model.CourseInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements CourseDao {
    
    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public List<CourseInterface> getAllCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }
    
}
