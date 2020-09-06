package com.github.perscholas.service;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.RepositoryInterface;
import com.github.perscholas.model.CourseInterface;

import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService extends AbstractService implements CourseDao {


    public CourseService(RepositoryInterface repository) {
        super(repository);
    }

    @Override
    public List<CourseInterface> getAllCourses() {
        return findAll();
    }
}