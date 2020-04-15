package com.boot.service.impl;

import com.boot.mapper.Mapper;
import com.boot.pojo.Teacher;
import com.boot.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-15 22:15
 */
@Service
public class MapperServiceImpl implements MapperService {
    @Autowired
    private Mapper mapper;

    @Override
    public List<Teacher> testSelectAllTeacher() {
        return mapper.testSelectAllTeacher();
    }
}
