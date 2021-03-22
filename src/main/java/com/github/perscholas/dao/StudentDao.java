package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;

import java.util.List;


/**
 * @author leonhunter
 * @created 02/12/2020 - 5:55 PM
 */
public interface StudentDao {
    /**
     * reads the student table in database
     * @return database data as a List<Student>
     */
    List<StudentInterface> getAllStudents();

    /**
     * takes a Student’s email as a String and parses the student list for a Student with that email and returns a Student Object.
     * @param studentEmail - student's email to be parsed
     * @return the student list of a Student with respective `studentEmail`
     */
    StudentInterface getStudentByEmail(String studentEmail);

    /**
     * This method takes two parameters: the first one is the user email and the second one is the password from the user input.
     * @param studentEmail - email student uses to log in
     * @param password - password student uses to log in
     * @return `true` if a student was found; else `false`
     */
    Boolean validateStudent(String studentEmail, String password);

    /**
     * After a successful student validation, this method takes a Student’s email and a Course ID.
     * It checks in the join table (i.e. Student_Course) generated by JPA to find if a Student with that Email is currently attending a Course with that ID.
     * If the Student is not attending that Course, register the student to that course; otherwise not
     * @param studentEmail - email student uses to log in
     * @param courseId - id of course student wishes to register to
     */
    void registerStudentToCourse(String studentEmail, int courseId);

    /**
     * This method takes a Student’s Email as a parameter and would find all the courses a student is registered.
     * @param studentEmail - student's email to be parsed
     * @return list of courses student has registered to
     */
    List<CourseInterface> getStudentCourses(String studentEmail);
}
