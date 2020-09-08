CREATE TABLE Management_system.`studentcourse`(
id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
courseId INT NOT NULL,
email VARCHAR(50) NOT NULL,
FOREIGN KEY (courseId)
REFERENCES course(id),
FOREIGN KEY (email)
REFERENCES student(email),
Unique (courseId,email)
);