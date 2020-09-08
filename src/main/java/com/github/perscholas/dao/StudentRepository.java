package com.github.perscholas.dao;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.Student;
import com.github.perscholas.utils.IOConsole;

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
            IOConsole.ERROR.println("Student does not exists");
            entityTransaction.rollback();
            entityManager.close();
            return;
        }
        Student student = opStudent.get();
        for (int index = 0; student.getCourses().size() > index; index++) {
            Course course = student.getCourses().get(index);
            if (course.getId() == courseId) {
                IOConsole.ERROR.println("Already enrolled into course");
                entityTransaction.rollback();
                entityManager.close();
                return;
            }
        }
        Optional<Course> opCourse = courseRepository.findBy("id", courseId);
        if (opCourse.isPresent()) {
            Course course = opCourse.get();
            student.getCourses().add(course);
            entityManager.merge(student);
            IOConsole.SUCCESS.println("Enrolling into course");
        }   else{
            IOConsole.ERROR.println("Course does not exist...");
        }
        entityTransaction.commit();
        entityManager.close();
    }
}
