package com.github.perscholas.dao;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.Student;

import javax.persistence.*;
import java.util.Optional;

public class StudentRepository extends AbstractJpaRepository<Student, String> {
    
    private final CourseRepository courseRepository;
    
    public StudentRepository(EntityManagerFactory entityManagerFactory, CourseRepository courseRepository) {
        super(entityManagerFactory);
        this.courseRepository = courseRepository;
    }
    
    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
    
    public Optional<Student> findByEmailAndPassword(String studentEmail, String password) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Student> typedQuery = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email AND s.password = :password", getEntityClass());
        typedQuery.setParameter("email", studentEmail);
        typedQuery.setParameter("password", password);
        Optional<Student> opStudent = Optional.empty();
        try {
            opStudent = Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exception) {
        
        }
        entityManager.close();
        return opStudent;
    }
    
    public void registerStudentToCourse(String studentEmail, int courseId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Optional<Student> opStudent = findBy("email", studentEmail);
        if (!opStudent.isPresent()) {
            entityTransaction.rollback();
            entityManager.close();
            return;
        }
        Student student = opStudent.get();
        for (int index = 0; student.getCourses().size() > index; index++) {
            Course course = student.getCourses().get(index);
            if (course.getId() == courseId) {
                entityTransaction.rollback();
                entityManager.close();
                return;
            }
        }
        Optional<Course> opCourseEntity = courseRepository.findBy("id", courseId);
        opCourseEntity.ifPresent(course -> {
            student.getCourses().add(course);
            entityManager.merge(student);
        });
        entityTransaction.commit();
        entityManager.close();
    }
}
