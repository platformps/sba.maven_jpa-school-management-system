package com.github.perscholas;

import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();
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
            displayOptions();
            input = console.getStringInput("");
            if (!DashboardOption.isValid(input)) {
                console.println("Invalid Option, please try again...");
                continue;
            }
            lastDashboardOption = DashboardOption.valueOf(input);
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
        console.print("\nWelcome to the Login Dashboard!");
        String studentEmail = console.getStringInput("Enter your email:");
        String studentPassword = console.getStringInput("Enter your password:");
        boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
        
        if (isValidLogin) {
            this.loggedInStudentEmail = studentEmail;
            this.currentDashboardOption = LOGGED_IN_OPTIONS;
            console.println(String.format("SUCCESSFULLY logged in as %s", loggedInStudentEmail));
        } else {
            console.println(String.format("Failed login, returning to menu."));
        }
    }
    
    private void debug() {
        String input = console.getStringInput(new StringBuilder()
                .append("Welcome to the Debug Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n[ student ] [ course ]").toString());
        if ("student".equals(input)) {
            console.println(studentService.getAllStudents().stream().map(StudentInterface::toString).collect(Collectors.joining("\n")));
        } else if ("course".equals(input)) {
            console.println(courseService.getAllCourses().stream().map(CourseInterface::toString).collect(Collectors.joining("\n")));
        } else {
            console.println(String.format("Invalid input %s", input));
        }
        console.println("Returning to Main Dashboard");
    }
    
    private void viewCourses() {
        List<CourseInterface> courses = studentService.getStudentCourses(loggedInStudentEmail);
        console.println(new StringBuilder()
                .append(String.format("[ %s ] is registered to the following courses:", loggedInStudentEmail))
                .append("\n\t" + courses)
                .toString());
    }
    
    public void register() {
        List<String> courseIds = courseService.getAllCourses().stream().map(CourseInterface::getId).map(value -> value.toString()).collect(Collectors.toList());
        Integer courseId = console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t" + courseIds
                        .toString()
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll(", ", "\n\t"))
                .toString());
        studentService.registerStudentToCourse(loggedInStudentEmail, courseId);
    }
    
    public void logout() {
        loggedInStudentEmail = null;
        currentDashboardOption = LOGGED_OUT_OPTIONS;
    }
    
    public boolean isStudentLoggedIn() {
        return loggedInStudentEmail != null;
    }
    
    public void displayOptions() {
        String dashboardOptionsOutput = currentDashboardOption.stream().map(DashboardOption::display).collect(Collectors.joining(" "));
        
        console.println(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append(dashboardOptionsOutput).toString());
    }
}
