CREATE TABLE studentcourses(
studentEmail varchar(50) NOT NULL,
courseId int NOT NULL,
FOREIGN KEY(studentEmail) REFERENCES Student(email),
FOREIGN KEY(courseId) REFERENCES Course(id),
UNIQUE(studentEmail, courseId)
);
