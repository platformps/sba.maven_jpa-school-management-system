CREATE TABLE management_system.StudentCourse (
   Student_email varchar(50) NOT NULL,
  Course_id int not null,
  FOREIGN KEY (Student_email) REFERENCES Student(email),
  FOREIGN KEY (Course_id) REFERENCES Course(id)
);
