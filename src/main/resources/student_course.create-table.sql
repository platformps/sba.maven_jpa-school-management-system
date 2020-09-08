CREATE TABLE management_system.student_course (
	`email` VARCHAR(50) NOT NULL COMMENT 'FK to Student Email',
	`id` INT NOT NULL COMMENT 'FK to course id',
	CONSTRAINT `FK1_student_email` FOREIGN KEY (`email`) REFERENCES `student` (`email`),
	CONSTRAINT `FK2_course_id` FOREIGN KEY (`id`) REFERENCES `course` (`id`)
)