package com.boot.controller;

import com.boot.pojo.Teacher;
import com.boot.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Mango
 * @Date 2020-04-15 22:17
 */
@Controller
public class MapperController {
    @Autowired
    private MapperService mapperServiceImpl;

    @RequestMapping("testMapper")
    public void testMapper() {
        List<Teacher> teachers = mapperServiceImpl.testSelectAllTeacher();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}
