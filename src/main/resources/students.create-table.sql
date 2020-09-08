use management_system;

DROP TABLE IF EXISTS Students;

CREATE TABLE Students(
    email varchar(50) not null,
    name varchar(50) not null,
    password varchar(50) not null,
    PRIMARY KEY (email)
);