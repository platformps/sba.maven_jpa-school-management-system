package com.github.perscholas.service;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.dao.StudentRepository;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentService implements StudentDao {
    private final StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        if(studentRepository == null){
            throw new IllegalArgumentException("studentRepository must not be null");
        }
        this.studentRepository = studentRepository;
    }
    
    
    @Override
    public List<StudentInterface> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }
    
    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return studentRepository.findBy("email", studentEmail).orElse(null);
    }
    
    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        return studentRepository.findByEmailAndPassword(studentEmail, password).isPresent();
    }
    
    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        studentRepository.registerStudentToCourse(studentEmail, courseId);
    }
    
    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        Optional<Student> opStudent = studentRepository.findBy("email", studentEmail);
        if (opStudent.isPresent()) {
            return new ArrayList<>(opStudent.get().getCourses());
        }
        return Collections.EMPTY_LIST;
    }
}
