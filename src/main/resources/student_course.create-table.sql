
CREATE TABLE management_system.Student_Course (
    student_email VARCHAR(50) NOT NULL,
    course_id int NOT NULL,
    FOREIGN KEY(student_email) references Students(email),
    FOREIGN KEY (course_id) references Course(id)
    );