CREATE TABLE IF NOT EXISTS Student(
  email varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  PRIMARY KEY (email)
);
CREATE TABLE IF NOT EXISTS StudentCourses(
studentEmail varchar(50) NOT NULL,
courseId int NOT NULL,
FOREIGN KEY(studentEmail) REFERENCES Student(email),
FOREIGN KEY(courseId) REFERENCES Course(id),
UNIQUE(studentEmail, courseId)
);