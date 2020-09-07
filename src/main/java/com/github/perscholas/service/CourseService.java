package com.github.perscholas.service;


import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.*;
import com.github.perscholas.model.CourseInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao{

    private final DatabaseConnection dbc;

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public CourseService() {
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<CourseInterface> getAllCourses() {
        ResultSet resultSet = null;
        List<CourseInterface> listCourses = null;
        try {
            resultSet = dbc.executeQuery("SELECT id,name,instructor FROM course");
            if (resultSet != null) {
                listCourses = new ArrayList<CourseInterface>();
                while(resultSet.next()){
                    int id= resultSet.getInt(1);
                    String name= resultSet.getString(2);
                    String instructor= resultSet.getString(3);
                    CourseInterface course = new Course(id,name,instructor);
                    System.out.println("Before student to List" + course.toString());
                    listCourses.add(course);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        } finally{
            try{
                if(resultSet!=null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return listCourses;
     }
}
