CREATE TABLE StudentCourses(
    studentEmail varchar(50) NOT NULL PRIMARY KEY,
    courseId int NOT NULL PRIMARY KEY,
    FOREIGN KEY(studentEmail) REFERENCES Student(email),
    FOREIGN KEY(courseId) REFERENCES Course(id),
);