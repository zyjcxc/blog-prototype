package com.mqa.demo.blog.controller;


import com.mqa.demo.blog.domain.User;
import com.mqa.demo.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 主页控制器
 * @author mengqa
 * @create 2018-05-07 14:22
 **/
@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String login(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败， 用户名或者密码错误");
        return "login";
    }

    /**
     * 获取注册页面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册用户
     * @return
     */
    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }



}

