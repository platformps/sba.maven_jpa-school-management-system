DROP TABLE IF EXISTS COURSE;

CREATE TABLE COURSE(
    id int not null,
    name varchar(50) not null,
    instructor varchar(50) not null,
    PRIMARY KEY (id)
);