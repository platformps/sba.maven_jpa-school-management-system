package com.github.perscholas.model;

public final class CourseBuilder {
    private Integer id;
    private String name;
    private String instructor;

    public CourseBuilder() {
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public CourseBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withInstructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public Course build() {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setInstructor(instructor);
        return course;
    }
}
