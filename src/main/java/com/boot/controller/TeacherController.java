package com.boot.controller;

import com.boot.pojo.User;
import com.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mango
 * @Date 2020-04-03 18:43
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherServiceImpl;

    @RequestMapping("admin_teacher")
    public String admin_teacher(Model model) {
        model.addAttribute("teachers", teacherServiceImpl.selectAllTeacher());
        return "admin_teacher";
    }

    @RequestMapping("delete_teacher")
    public String delete_teacher(Integer id, Model model) {
        if (teacherServiceImpl.deleteTeacher(id) == 1) {
            model.addAttribute("tip", "删除成功");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAllTeacher());
        return "admin_teacher";
    }

    @RequestMapping("update_teacher")
    public String update_teacher(User user, Model model) {
        if (teacherServiceImpl.updateTeacher(user) == 1) {
            model.addAttribute("tip", "更新教师信息成功");
        }
        model.addAttribute("teachers", teacherServiceImpl.selectAllTeacher());
        return "admin_teacher";
    }
}