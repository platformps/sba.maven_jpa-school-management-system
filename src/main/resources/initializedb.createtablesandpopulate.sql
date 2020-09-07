CREATE DATABASE IF NOT EXISTS uat;
CREATE TABLE uat.Student (
    email VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (email)
);
CREATE TABLE uat.Course(
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    instructor VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE uat.StudentCourse(
    studentEmail VARCHAR(50) NOT NULL,
    courseId INT NOT NULL,
    PRIMARY KEY (studentEmail, courseId)
);
