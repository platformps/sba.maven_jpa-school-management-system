package com.github.perscholas;

public enum DashboardOption {
    LOGIN("Login Dashboard"), DEBUG("Debug Screen"), VIEW("View Your Courses Dashboard"),
    REGISTER("Registration Dashboard"), LOGOUT("Logout of Account"), EXIT("Exit Application");
    
    private String title;
    
    private DashboardOption(String title) {
        this.title = title;
    }
    
    public static boolean isValid(String value) {
        try {
            DashboardOption.valueOf(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public String display() {
        return String.format("[ %s ]", toString());
    }
}
