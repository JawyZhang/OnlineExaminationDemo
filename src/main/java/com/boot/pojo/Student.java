package com.boot.pojo;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 16:42
 */
public class Student {
    private Integer id;
    private String username;
    private String password;
    private List<Course> courses;    //学生查询所有课程

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> course) {
        this.courses = course;
    }
}
