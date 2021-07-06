create table StudentCourse
(
 course_id int,
  constraint  course_type
foreign key (course_id)
references Course(id),
student_email varchar(50),
constraint email
foreign key (student_email)
references Student(email)
)