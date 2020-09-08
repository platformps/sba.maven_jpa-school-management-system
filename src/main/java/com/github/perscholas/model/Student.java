package com.github.perscholas.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
@Table(name = "Student")
public class Student implements StudentInterface {

    @Id
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;
}
