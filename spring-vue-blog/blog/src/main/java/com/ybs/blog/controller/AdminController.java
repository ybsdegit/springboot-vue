package com.ybs.blog.controller;

import com.ybs.blog.enums.ResultEnum;
import com.ybs.blog.pojo.Admin;
import com.ybs.blog.utils.Result;
import com.ybs.blog.utils.ShiroUtils;
import com.ybs.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * AdminController
 *
 * @author Paulson
 * @date 2020/3/26 2:37
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public Result<Object> login(@RequestBody Admin admin){
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());

        try {
            subject.login(authenticationToken);
        }catch (Exception e){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }


    /**
     * 获取当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<Admin> getLoginInfo(){
        Admin admin = (Admin) ShiroUtils.getLoginUser();
        admin.setPassword("");
        return new Result<>(admin);
    }
}
