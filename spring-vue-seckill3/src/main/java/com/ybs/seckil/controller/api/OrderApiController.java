package com.ybs.seckil.controller.api;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderlist")
    public Result<List<Orders>> courseList(User user){
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");


        return Result.success(orderService.findAllByUsername(user.getUsername(), sort));
    }

}
