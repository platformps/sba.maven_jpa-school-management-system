CREATE TABLE IF NOT EXISTS School_Management_System.intermediate (
    student_email VARCHAR(50) NOT NULL,
    course_id INT NOT NULL,
    CONSTRAINT fk_student_email FOREIGN KEY (student_email) REFERENCES Student(email),
    CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES Course(id)
);