package com.github.perscholas.dao;

import com.github.perscholas.model.Course;

import javax.persistence.EntityManagerFactory;

public class CourseRepository extends AbstractJpaRepository<Course, Integer>{
    
    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }
    
    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }
}
