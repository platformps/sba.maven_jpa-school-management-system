use management_system;

DROP TABLE IF EXISTS Student;

CREATE TABLE Student(
    email varchar(50) not null,
    name varchar(50) not null,
    password varchar(50) not null,
    PRIMARY KEY (email)
);