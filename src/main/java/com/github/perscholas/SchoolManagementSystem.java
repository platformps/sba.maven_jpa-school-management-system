package com.github.perscholas;

import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolManagementSystem implements Runnable {
    private final StudentService studentService;
    private final CourseService courseService;
    private String loggedInStudentEmail;
    private DashboardOption lastDashboardOption;
    private final EntityManagerFactory entityManagerFactory;
    
    public static final List<DashboardOption> LOGGED_IN_OPTIONS = Arrays.asList(DashboardOption.VIEW, DashboardOption.REGISTER, DashboardOption.LOGOUT, DashboardOption.DEBUG);
    public static final List<DashboardOption> LOGGED_OUT_OPTIONS = Arrays.asList(DashboardOption.LOGIN, DashboardOption.EXIT, DashboardOption.DEBUG);
    public List<DashboardOption> currentDashboardOption = LOGGED_OUT_OPTIONS;
    
    public SchoolManagementSystem(StudentService studentService, CourseService courseService, EntityManagerFactory entityManagerFactory) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.entityManagerFactory = entityManagerFactory;
    }
    
    
    @Override
    public void run() {
        String input;
        do {
            input = displayOptions();
            if (!DashboardOption.isValid(input)) {
                IOConsole.ERROR.println("Invalid input, please try again...\n");
                continue;
            }
            lastDashboardOption = DashboardOption.valueOf(input);
            IOConsole.SUCCESS.println(String.format("Successful input %s\n", lastDashboardOption.display()));
            switch (lastDashboardOption) {
                case LOGIN:
                    login();
                    break;
                case VIEW:
                    viewCourses();
                    break;
                case REGISTER:
                    register();
                    break;
                case LOGOUT:
                    logout();
                    break;
                case DEBUG:
                    debug();
                    break;
                case EXIT:
                    exit();
                    break;
            }
        } while (lastDashboardOption != DashboardOption.EXIT);
    }
    
    private void exit() {
        entityManagerFactory.close();
    }
    
    private void login() {
        IOConsole.NORMAL.print("Welcome to the Login Dashboard!e");
        String studentEmail = IOConsole.NORMAL.getStringInput("\nEnter your email:");
        String studentPassword = IOConsole.NORMAL.getStringInput("Enter your password:");
        boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
        
        if (isValidLogin) {
            this.loggedInStudentEmail = studentEmail;
            this.currentDashboardOption = LOGGED_IN_OPTIONS;
            IOConsole.SUCCESS.println(String.format("logged in as %s\n", loggedInStudentEmail));
        } else {
            IOConsole.ERROR.println(String.format("Failed login, returning to menu.\n"));
        }
    }
    
    private void debug() {
        String input = IOConsole.NORMAL.getStringInput(new StringBuilder()
                .append("Welcome to the Debug Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n[ student ] [ course ]").toString());
        if ("student".equals(input)) {
            IOConsole.DATABASE.println(studentService.getAllStudents().stream().map(StudentInterface::toString).collect(Collectors.joining("\n")));
        } else if ("course".equals(input)) {
            IOConsole.DATABASE.println(courseService.getAllCourses().stream().map(CourseInterface::toString).collect(Collectors.joining("\n")));
        } else {
            IOConsole.ERROR.println(String.format("\nInvalid input %s", input));
        }
        IOConsole.NORMAL.println("\nReturning to Main Dashboard");
    }
    
    private void viewCourses() {
        List<CourseInterface> courses = studentService.getStudentCourses(loggedInStudentEmail);
        IOConsole.DATABASE.println(new StringBuilder()
                .append(String.format("[ %s ] is registered to the following courses:\n", loggedInStudentEmail))
                .append(courses.stream().map(CourseInterface::toString).map(output -> "\t"+output).collect(Collectors.joining("\n")))
                .append("\n")
                .toString());
    }
    
    public void register() {
        String coursesOutput = courseService.getAllCourses().stream().map(CourseInterface::toString).map(output -> "\t"+output).collect(Collectors.joining("\n"));
        Integer courseId = IOConsole.NORMAL.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, select ID of the course you would like to register for:")
                .append("\n" + coursesOutput)
                .toString());
        IOConsole.SUCCESS.println(String.format("Will try to register to course with id = %s", courseId));
        studentService.registerStudentToCourse(loggedInStudentEmail, courseId);
        IOConsole.NORMAL.println("");
    }
    
    public void logout() {
        loggedInStudentEmail = null;
        currentDashboardOption = LOGGED_OUT_OPTIONS;
    }
    
    public boolean isStudentLoggedIn() {
        return loggedInStudentEmail != null;
    }
    
    public String displayOptions() {
        String dashboardOptionsOutput = currentDashboardOption.stream().map(DashboardOption::display).collect(Collectors.joining(" "));
        
        return IOConsole.NORMAL.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append(dashboardOptionsOutput).toString());
    }
}
