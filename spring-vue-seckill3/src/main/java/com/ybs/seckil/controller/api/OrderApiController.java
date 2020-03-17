package com.ybs.seckil.controller.api;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class OrderApiController {

    @Autowired
    private SeckillService seckillService;


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

    }
