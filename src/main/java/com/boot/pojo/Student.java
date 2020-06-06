package com.boot.pojo;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-07 16:42
 */
public class Student {
    private Integer id;
    private String stu_no;
    private String username;
    private String password;
    private String class_room;
    private int status = 2;
    private String ip;
    private List<Course> courses;    //学生查询所有课程

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
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

    public String getClass_room() {
        return class_room;
    }

    public void setClass_room(String class_room) {
        this.class_room = class_room;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stu_no='" + stu_no + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", class_room='" + class_room + '\'' +
                ", status=" + status +
                ", ip='" + ip + '\'' +
                ", courses=" + courses +
                '}';
    }
}
