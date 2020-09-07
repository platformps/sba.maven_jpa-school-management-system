CREATE TABLE Course (
    /** Unique course identifier **/
    id INT NOT NULL PRIMARY KEY,

    /** Provides the name of the course **/
    name VARCHAR(50) NOT NULL,

    /** Provides the name of the instructor **/
    instructor VARCHAR(50) NOT NULL
);