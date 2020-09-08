CREATE TABLE StudentCourse(
    studentEmail VARCHAR(50) NOT NULL,
    courseId INT NOT NULL,
    PRIMARY KEY (studentEmail, courseId)
); 