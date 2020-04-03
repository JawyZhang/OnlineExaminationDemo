package com.boot.service;

import com.boot.pojo.User;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
public interface TeacherService {
    /**
     * 查询所有的教师
     *
     * @return
     */
    List<User> selectAllTeacher();

    /**
     * 根据提供的ID删除对应的教师用户
     *
     * @param id
     * @return
     */
    Integer deleteTeacher(Integer id);

    /**
     * 根据提供的教师信息更新相应的教师信息
     *
     * @param user
     * @return
     */
    Integer updateTeacher(User user);
}
