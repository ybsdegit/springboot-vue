package com.ybs.note.controller;

import com.ybs.note.pojo.User;
import com.ybs.note.response.Response;
import com.ybs.note.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author Paulson
 * @date 2020/3/22 20:35
 */

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find_user/{username}")
    public User findUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/register")
    public Response Register(@RequestBody User user){
        boolean existUser = userService.isExistUser(user.getUsername());
        if (existUser){
            return new Response(502,"用户名已存在");
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String password = new SimpleHash(algorithm, user.getPassword(), salt, times).toString();

        user.setSalt(salt);
        user.setPassword(password);

        userService.addUser(user);
        return new Response("注册成功");
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user){
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return new Response(200, "登录成功", token);
        }catch (UnknownAccountException e){
            return new Response(500, "用户名不存在", token);
        }catch (IncorrectCredentialsException e){
            return new Response(501, "密码错误", token);
        }catch (AuthenticationException e){
            return new Response(502, "用户名不存在或密码错误", token);
        }
    }


}
