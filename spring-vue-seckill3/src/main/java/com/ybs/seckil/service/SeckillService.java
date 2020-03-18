package com.ybs.seckil.service;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.model.Course;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * SeckillService
 *
 * @author Paulson
 * @date 2020/3/17 21:01
 */

public interface SeckillService {
    Result<Orders> seckillFlow(User user, String courseNo);

    Orders seckill(User user, Course course);

    Result<Orders> seckillResult(User user, String courseNo);

    void cacheAllCourse();

    String getPath(User user, String courseNo);

    Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);
}
