package com.boot.controller;

import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UesrController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/show")
    public String show(Model model) {
        model.addAttribute("users", userServiceImpl.selectAllUser());
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }
}
