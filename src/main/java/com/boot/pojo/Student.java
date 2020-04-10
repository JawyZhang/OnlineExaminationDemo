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
    private List<Class> classes;

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

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
