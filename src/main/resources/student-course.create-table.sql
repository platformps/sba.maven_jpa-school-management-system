CREATE TABLE Management_system.`student-course`(
id INT NOT NULL PRIMARY KEY,
courseId INT NOT NULL,
email VARCHAR(50) NOT NULL,
FOREIGN KEY (courseId)
REFERENCES course(id),
FOREIGN KEY (email)
REFERENCES student(email)
);