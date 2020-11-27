package com.github.perscholas.model;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.*;

//* Column1
//        * Column Name: `email`
//        * Column Data-Type: `varchar(50) not null (PK)`
//        * Column Description: Student's current school email, unique student identifier
//        * Column2
//        * Column Name: `name`
//        * Column Data-Type: `varchar(50) not null`
//        * Column Description: The full name of the student
//        * Column3
//        * Column Name: `password`
//        * Column Data-Type: `varchar(50) not null`
//        * Column Description: Student's password used to log in


// TODO - Annotate and Implement respective interface and define behaviors

    @Entity
    @Table(name="Student")
public class Student implements StudentInterface{

        @Id
        private String email;
        private String name;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Student(){

        }

        public Student(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
        }

    }
