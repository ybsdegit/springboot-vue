package com.ybs.seckil.controller;

import com.ybs.seckil.model.User;
import com.ybs.seckil.service.UserService;
import com.ybs.seckil.util.MD5Util;
import com.ybs.seckil.util.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * RegisterController
 *
 * @author Paulson
 * @date 2020/3/12 21:48
 */

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        User user = new User();
        return new ModelAndView("login").addObject(user);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(value = "user") User user, HttpSession session, String code, Model model){
        log.info("{},{}",user.getUsername(), user.getPassword());

        String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsAnyIgnoreCase(code, sessionCode)){
            model.addAttribute("message", "验证码不匹配");
            return "login";
        }

        User dbUser = userService.getUser(user.getUsername());
        if (dbUser != null){
            if (dbUser.getPassword().equals(MD5Util.backToDb(user.getPassword(), "ybs"))){
                session.setAttribute("user", dbUser);
                return "home";
            }else {
                return "login";
            }
        }
       return "login";
    }

    @GetMapping("/validateCode")
    public String validateCode(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        HttpSession session = request.getSession();
        ValidateCode validateCode = new ValidateCode(120, 34, 5, 100);
        session.setAttribute("code", validateCode.getCode());
        validateCode.write(response.getOutputStream());
        return null;
    }
}
