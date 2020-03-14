package com.ybs.seckil.controller.api;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.base.result.ResultCode;
import com.ybs.seckil.model.Course;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.CourseService;
import com.ybs.seckil.service.UserService;
import com.ybs.seckil.util.MD5Util;
import com.ybs.seckil.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
public class CourseApiController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courseList")
    public Result<List<Course>> courseList(){
        return Result.success(courseService.findAllCourse());
    }
}
