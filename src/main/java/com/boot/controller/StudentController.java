package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Mango
 * @Date 2020-04-21 18:20
 */
@Controller
public class StudentController {
    @RequestMapping("student_home")
    public String student_home(Model model, String tip) {
        model.addAttribute("tip", tip);
        return "student_home";
    }
}
