CREATE TABLE STUDENT(
    /** Student's current school email, unique student identifier **/
    EMAIL VARCHAR(50) NOT NULL PRIMARY KEY,

    /** The full name of the student **/
    NAME VARCHAR(50) NOT NULL,

    /** Student's password used to log in **/
    PASSWORD VARCHAR(50) NOT NULL
);