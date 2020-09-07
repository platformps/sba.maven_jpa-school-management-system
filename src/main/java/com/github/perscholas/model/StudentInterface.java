package com.github.perscholas.model;

public interface StudentInterface extends EntityInterface<String>{
    String getEmail();
    String getName();
    String getPassword();


    void setEmail(String email);
    void setName(String name);
    void setPassword(String password);
}
