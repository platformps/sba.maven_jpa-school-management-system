package com.github.perscholas.model;

public class CourseBuilder {

    private String instructor;
    private Integer id;
    private String name;

    public CourseBuilder() {
    }

    public CourseBuilder(Course dataToBeUpdated) {
        this.id = dataToBeUpdated.getId();
        this.name = dataToBeUpdated.getName();
        this.instructor = dataToBeUpdated.getInstructor();
    }

    public CourseBuilder setId(Integer id) {
        this.id=id;
        return this;
    }

    public CourseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder setInstructor(String instructor) {
        this.instructor=instructor;
        return this;
    }



    public Course build() {
        return new Course(id, instructor,name);
    }
}
