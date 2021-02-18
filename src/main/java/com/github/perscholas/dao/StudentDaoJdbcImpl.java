package com.github.perscholas.dao;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//public class StudentDaoJdbcImpl implements StudentDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    private static final String INSERT_STUDENT_SQL =
//            "insert into student (email, name, password) values (?, ?, ?)";
//
//    private static final String SELECT_STUDENT_BY_EMAIL_SQL =
//            "select * from student where email = ?";
//
//    private static final String SELECT_ALL_STUDENTS_SQL =
//            "select * from student";
//
//    private static final String  SELECT_STUDENTS_BY_COURSE_SQL =
//            "select * from StudentCourse where student_email = ?";
//    private Object StudentEmail;
//
//
//    @Autowired
//    public StudentDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Transactional
//    public Student addStudent(Student student) {
//
//        jdbcTemplate.update(
//                INSERT_STUDENT_SQL,
//                student.getEmail(),
//                student.getName(),
//                student.getPassword());
//
//        //int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//
//        //student.setId(id);  If we had an Id field...We are using email as the Primary Key in student table.
//
//        return student;
//
//    }
//
//    @Override
//    public Student getStudentByEmail(String email) {
//
//        try {
//            return jdbcTemplate.queryForObject(
//                    SELECT_STUDENT_BY_EMAIL_SQL,
//                    this::mapRowToStudent,
//                    email);
//        } catch (EmptyResultDataAccessException e) {
//            // if there is no entry with the given id, just return null
//            return null;
//        }
//    }
//
//    @Override
//    public Boolean validateStudent(String studentEmail, String password) {
//
//        return getAllStudents().stream()
//                .filter(students -> students.getEmail().equals(studentEmail) && students.getPassword().equals(password))
//                .count() == 1 ? true : false;
//    }
//
//    @Override
//    public void registerStudentToCourse(String studentEmail, int courseId) {
//
//    }
//
//    @Override
//    public List<StudentInterface> getAllStudents() {
//
//        return jdbcTemplate.query(
//                SELECT_ALL_STUDENTS_SQL,
//                this::mapRowToStudent);
//    }
//
//    @Override
//    public List<CourseInterface> getStudentCourses(String studentEmail) {
//
//        return  jdbcTemplate.query(
//                SELECT_STUDENTS_BY_COURSE_SQL,
//                this::mapRowToStudentCourse,
//                StudentEmail);
//    }
//
//
//    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException {
//        Student student = new Student();
//        student.setEmail(rs.getString("email"));
//        student.setName(rs.getString("name"));
//        student.setPassword(rs.getString("password"));
//
//        return student;
//    }
//    private Course mapRowToStudentCourse(ResultSet rs, int rowNum) throws SQLException {
//        Course course = new Course();
//
//        course.setId(rs.getInt("course_id"));
//        course.setName(rs.getString("student_email"));
//
//        return course;
//    }
//}



