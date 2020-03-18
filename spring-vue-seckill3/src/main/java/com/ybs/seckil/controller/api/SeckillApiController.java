package com.ybs.seckil.controller.api;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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
public class SeckillApiController implements InitializingBean {

    @Autowired
    private SeckillService seckillService;

    @Override
    public void afterPropertiesSet() throws Exception {
        seckillService.cacheAllCourse();

    }


    @GetMapping("{path}/seckill/{courseNo}")
    public Result<Orders> seckill2(User user,
                                   @PathVariable String courseNo,
                                   @PathVariable String path,
                                   HttpServletRequest request){
        if (user == null){
            return Result.failure();
        }
        return seckillService.seckillFlow(user, courseNo, path, request);
    }

    @GetMapping("/seckill/{courseNo}")
    public Result<Orders> seckill(User user, @PathVariable String courseNo){
        if (user == null){
            return Result.failure();
        }
        return seckillService.seckillFlow(user, courseNo);
    }

    @GetMapping("/seckillResult/{courseNo}")
    public Result<Orders> seckillResult(User user, @PathVariable String courseNo){
        if (user == null){
            return Result.failure();
        }

        return seckillService.seckillResult(user, courseNo);
    }

    @GetMapping("getPath/{courseNo}")
    public String getPath(User user, @PathVariable String courseNo){
        if (user == null){
            return "Result.failure()";
        }
        return seckillService.getPath(user, courseNo);
    }


    }
