DROP TABLE IF EXISTS Student CASCADE;

CREATE TABLE Student(
    /** Student's current school email, unique student identifier **/
    email VARCHAR(50) NOT NULL PRIMARY KEY,

    /** The full name of the student **/
    name VARCHAR(50) NOT NULL,

    /** Student's password used to log in **/
    password VARCHAR(50) NOT NULL
);