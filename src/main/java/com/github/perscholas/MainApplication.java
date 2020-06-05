package com.github.perscholas;

public class MainApplication {
    public static void main(String[] args) {
        JdbcConfigurator.initialize();
        Runnable sms = new SchoolManagementSystem();
        sms.run();
    }
}
