package com.github.perscholas.dao;


import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentBuilder;

public class StudentRepository extends AbstractJpaRepository<String, Student> {
    public StudentRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student update(Student dataToBeUpdated, Student newEntityData) {
        return new StudentBuilder(dataToBeUpdated)
                .setEmail(newEntityData.getEmail())
                .setName(newEntityData.getName())
                .setPassword(newEntityData.getPassword())
                .build();
    }
}