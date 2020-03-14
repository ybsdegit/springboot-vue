package com.ybs.seckil.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ybs.seckil.model.User;
import com.ybs.seckil.redis.UserRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController
 *
 * @author Paulson
 * @date 2020/3/13 1:46
 */

@Controller
public class HomeController {

    @Autowired
    private UserRedis userRedis;

    @GetMapping("/home")
    public String home(Model model, User user) {
        System.out.println(user);
        model.addAttribute("user", user);
        return "home";
    }
}
