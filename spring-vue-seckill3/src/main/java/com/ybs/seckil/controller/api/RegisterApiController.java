package com.ybs.seckil.controller.api;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.base.result.ResultCode;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.UserService;
import com.ybs.seckil.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
public class RegisterApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Object> register(@ModelAttribute(value = "user") User user){
        user.setPassword(MD5Util.backToDb(user.getPassword(), "ybs"));
        User register = userService.register(user);
        if (register.getId().equals(0)){
            return Result.success(ResultCode.USER_REGISTER_ERROR);
        }
        return Result.success(ResultCode.USER_REGISTER_SUCCESS);
    }
}
