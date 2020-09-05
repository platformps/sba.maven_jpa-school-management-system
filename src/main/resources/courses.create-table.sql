CREATE TABLE COURSE (
    /** Unique course identifier **/
    ID INT NOT NULL PRIMARY KEY,

    /** Provides the name of the course **/
    NAME VARCHAR(50) NOT NULL,

    /** Provides the name of the instructor **/
    INSTRUCTOR VARCHAR(50) NOT NULL
);