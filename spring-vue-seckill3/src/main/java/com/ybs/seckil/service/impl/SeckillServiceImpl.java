package com.ybs.seckil.service.impl;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.base.result.ResultCode;
import com.ybs.seckil.model.Course;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.CourseService;
import com.ybs.seckil.service.OrderService;
import com.ybs.seckil.service.SeckillService;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * SeckillServiceImpl
 *
 * @author Paulson
 * @date 2020/3/17 21:02
 */

@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public Result<Orders> seckillFlow(User user, String courseNo) {
        // 判断库存
        Course course = courseService.findCourseByCourseNo(courseNo);
        int stock = course.getStockQuantity();
        if (stock <= 0){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        // 判断是否已经购买
        Orders orders = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if (orders != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }

        // 减少库存，下订单(直接调数据库)
        // Orders newOrder = seckill(user, course);

        // 减少库存，下订单(使用kafka)
        //减库存 下订单
        kafkaTemplate.send("test",courseNo+","+user.getUsername());
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }


    @Override
    public Orders seckill(User user, Course course){
        // 减少库存，下订单
        int success = courseService.reduceStockByCourseNo(course.getCourseNo());
        if (success > 0){
            Orders orders = new Orders();
            BeanUtils.copyProperties(course, orders);
            orders.setUsername(user.getUsername());
            orders.setCreateBy(user.getUsername());
            orders.setCreateDate(new Date());
            orders.setPayPrice(course.getCourcePrice());
            orders.setPayStatus("0");
            return orderService.saveAndFlush(orders);
        }
        return null;
    }

    @Override
    public Result<Orders> seckillResult(User user, String courseNo) {
        Orders orders = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if (orders == null){
            return Result.failure(ResultCode.SECKILL_LINE_UP);
        }
        return Result.success(orders);
    }
}
