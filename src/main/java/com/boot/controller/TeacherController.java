package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mango
 * @Date 2020-04-03 18:43
 */
@Controller
public class TeacherController {
    @RequestMapping("teacher_home")
    public String teacher_home(Model model, String tip) {
        model.addAttribute("tip", tip);
        return "teacher_home";
    }
}