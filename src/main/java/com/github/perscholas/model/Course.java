package com.github.perscholas.model;
import javax.persistence.*;
import java.util.*;

// TODO - Annotate and Implement respective interface and define behaviors
// Using each of the interfaces provided in the `model` package as a guide, create a model for each of the aforementioned tables.
//         * Use the appropriate annotation to indicate
//         * the models are to be used as an `Entity`
//         * the name of the table each entity is based on
//         * the variable that is used as a primary key
//         * the relationships
//         * the name of the column each variable is based on each entity.
//         * Every Model class must contain the following general two requirements:
//        * A nullary constructor
//        * A non-nullary constructor which initializes every private member with a parameter provided to the constructor.
//        * entirely private fields
//        * public getters and setters


//Table 2 – Course
//        * Column1
//        * Column Name: `id`
//        * Column Data-Type: `int not null (PK)`
//        * Column Description: Unique course identifier
//        * Column2
//        * Column Name: `name`
//        * Column Data-Type: `varchar(50) not null`
//        * Column Description: provides the name of the course
//        * Column3
//        * Column Name: `instructor`
//        * Column Data-Type: `varchar(50) not null`
//        * Column Description: provides the name of the instructor

@Entity
@Table(name="course")
public class Course implements CourseInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String instructor;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public Course (){

    }
    public Course(int id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }


}
