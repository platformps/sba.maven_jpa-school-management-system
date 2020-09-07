CREATE table StudentCourse(
    studentEmail varchar(50) not null,
    foreign key (studentEmail) references Student(email),
    courseId int not null,
    foreign key (courseId) references Course(id),
)