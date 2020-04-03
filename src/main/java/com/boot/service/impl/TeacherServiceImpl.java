package com.boot.service.impl;

import com.boot.mapper.TeacherMapper;
import com.boot.pojo.User;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-03 18:46
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<User> selectAllTeacher() {
        return teacherMapper.selectAllTeacher();
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        return teacherMapper.deleteTeacher(id);
    }

    @Override
    public Integer updateTeacher(User user) {
        return teacherMapper.updateTeacher(user);
    }
}
