package com.github.perscholas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JpaConnectionBuilder {
    private String userName;
    private String userPass;
    private String url;
    
    public JpaConnectionBuilder setUser(String userName) {
        this.userName = userName;
        return this;
    }
    
    
    public JpaConnectionBuilder setPassword(String userPass) {
        this.userPass = userPass;
        return this;
    }
    
    public JpaConnectionBuilder setUrl(String url) {
        this.url = url;
        return this;
    }
    
    
    public Connection build() {
        try {
            return DriverManager.getConnection(url, this.userName, this.userPass);
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to connect to `%s`", url);
            throw new Error(errorMessage, e);
        }
    }
}