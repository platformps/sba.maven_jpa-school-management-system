package com.github.perscholas.dao;

import com.github.perscholas.model.*;

import java.util.List;

public class CourseRepository extends AbstractJpaRepository<Integer,Course> {

        public CourseRepository(String persistenceUnitName) {
            super(persistenceUnitName);
        }

        @Override
        public Class<Course> getEntityClass() {
            return Course.class;
        }

        @Override
        public Course update(Course dataToBeUpdated, Course newEntityData) {
            return new CourseBuilder(dataToBeUpdated)
                    .setId(newEntityData.getId())
                    .setName(newEntityData.getName())
                    .setInstructor(newEntityData.getInstructor())
                    .build();
        }


}

