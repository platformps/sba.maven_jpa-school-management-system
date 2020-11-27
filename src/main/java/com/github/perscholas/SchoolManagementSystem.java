package com.github.perscholas;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;
import java.util.*;
import java.util.stream.Collectors;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();

    @Override
    public void run() {
        StudentDao studentService=new StudentService();
        String smsDashboardInput;
        do {
            smsDashboardInput = getSchoolManagementSystemDashboardInput();
            if ("login".equals(smsDashboardInput)) {
                String studentEmail = console.getStringInput("Enter your email:");
                String studentPassword = console.getStringInput("Enter your password:");
                Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
                if (isValidLogin) {
                    String studentDashboardInput = getStudentDashboardInput();

                    if("courses".equals(studentDashboardInput)){
                        List<CourseInterface> courses =  studentService.getStudentCourses(studentEmail);
                        console.println("Registered courses: ");
                        console.println("Id   Course Name      Instructor Name");
                        for (CourseInterface course : courses) {
                            console.println(course.getId() + "    " + course.getName() + "    " +  course.getInstructor());
                        }
                    }

                    if ("register".equals(studentDashboardInput)) {
                        Integer courseId = getCourseRegistryInput();
                        studentService.registerStudentToCourse(studentEmail, courseId);
                        String studentCourseViewInput = getCourseViewInput();
                        if ("view".equals(studentCourseViewInput)) {
                            console.println("Registered courses: ");
                            console.println("Id   Course Name      Instructor Name");
                            List<CourseInterface> courses =  studentService.getStudentCourses(studentEmail);
                           courses.forEach(course -> {
                               console.println(course.getId() + "    " + course.getName() + "    " +  course.getInstructor());
                           });
                        }
                    }
                }else{
                    console.println("Invalid User Credentials.");
                    System.exit(0);
                }
            }
        } while (!"logout".equals(smsDashboardInput));
    }

    private String getCourseViewInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Course View Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ view ], [ logout ]")
                .toString());
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t['courses'], [ register ], [ logout]")
                .toString());
    }


    private Integer getCourseRegistryInput(){
        List<CourseInterface> allCourses = new CourseService().getAllCourses();

        StringBuilder baseMessage = new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options: \n");

        for(int i = 0; i < allCourses.size(); i++){
            baseMessage.append( allCourses.get(i).getId() + ".   " + allCourses.get(i).getName() + "    " +  allCourses.get(i).getInstructor() + " \n");
        }

        return console.getIntegerInput(baseMessage.toString());



    }
}
