package com.boot.controller;

import com.boot.pojo.User;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author Mango
 * @Date 2020-04-03 18:44
 */
@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/login")
    private String login(Model model, User user, HttpSession session) {
        User user1 = userServiceImpl.selectUser(user);
        if (user1 != null) {
            session.setAttribute("user", user1);
            session.removeAttribute("tip");
            if (user1.getStatus() == 0) {
                return "admin_home";
            }
            else if(user1.getStatus()==1){
                return "教师页";//该页面暂时不存在
            }
            else {
                return "学生页";//该页面暂时不存在
            }
        }
        session.setAttribute("user", user);
        session.setAttribute("tip", "密码或用户名错误，请重试！");
        return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "index";
    }
}