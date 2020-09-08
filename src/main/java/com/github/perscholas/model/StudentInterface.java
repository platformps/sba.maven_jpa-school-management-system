package com.github.perscholas.model;

import java.io.Serializable;

public interface StudentInterface {
    String getEmail();
    String getName();
    String getPassword();

    void setEmail(String email);
    void setName(String name);
    void setPassword(String password);
}
