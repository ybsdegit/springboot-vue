package com.ybs.seckil.controller;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.base.result.ResultCode;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.UserService;
import com.ybs.seckil.util.MD5Util;
import com.ybs.seckil.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * RegisterController
 *
 * @author Paulson
 * @date 2020/3/12 21:48
 */

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class LoginApiController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result<Object> login(@ModelAttribute(value = "user") User user,
                        HttpServletResponse response) {
        log.info("{},{}", user.getUsername(), user.getPassword());

        /*String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsAnyIgnoreCase(code, sessionCode)) {
            model.addAttribute("message", "验证码不匹配");
            return "login";
        }*/
        User dbUser = userService.getUser(user.getUsername());
        if (dbUser != null) {
            if (dbUser.getPassword().equals(MD5Util.backToDb(user.getPassword(), "ybs"))) {
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();
            } else {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        }
        return Result.failure(ResultCode.USER_LOGIN_ERROR);
    }
}
