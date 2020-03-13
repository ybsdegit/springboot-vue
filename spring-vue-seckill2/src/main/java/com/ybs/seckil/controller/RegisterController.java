package com.ybs.seckil.controller;

import com.ybs.seckil.model.User;
import com.ybs.seckil.service.UserService;
import com.ybs.seckil.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * RegisterController
 *
 * @author Paulson
 * @date 2020/3/12 21:48
 */

@Controller
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public ModelAndView toRegister(){
        User user = new User();
        return new ModelAndView("register").addObject(user);
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute(value = "user") User user){
        user.setPassword(MD5Util.backToDb(user.getPassword(), "ybs"));
        user.setId(2);
        userService.register(user);
        return new ModelAndView("login");
    }
}
