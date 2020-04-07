package com.boot.service.impl;

import com.boot.mapper.StudentMapper;
import com.boot.pojo.Student;
import com.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Mango
 * @Date 2020-04-07 18:11
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectByUsernameAndPassword(Student student) {
        return studentMapper.selectByUsernameAndPassword(student);
    }

    @Override
    public Integer updateStudentById(Student student) {
        return studentMapper.updateStudentById(student);
    }
}
