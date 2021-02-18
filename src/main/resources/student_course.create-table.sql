CREATE TABLE Student_Course(
    student_email VARCHAR(50),
    FOREIGN KEY (student_email) REFERENCES Student(email),
    courses_id INT,
    FOREIGN KEY (courses_id) REFERENCES Course(id)
);