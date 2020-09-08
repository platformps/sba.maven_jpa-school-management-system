package com.github.perscholas.model;

import java.util.List;

public interface StudentInterface extends EntityInterface<String>{
    String getEmail();
    String getName();
    String getPassword();
    List<CourseInterface> getClasses();


    void setEmail(String email);
    void setName(String name);
    void setPassword(String password);
}
