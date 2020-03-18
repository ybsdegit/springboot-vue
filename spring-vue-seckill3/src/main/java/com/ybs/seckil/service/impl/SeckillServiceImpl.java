package com.ybs.seckil.service.impl;

import com.ybs.seckil.base.result.Result;
import com.ybs.seckil.base.result.ResultCode;
import com.ybs.seckil.model.Course;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.redis.CourseRedis;
import com.ybs.seckil.redis.SeckillRedis;
import com.ybs.seckil.service.CourseService;
import com.ybs.seckil.service.OrderService;
import com.ybs.seckil.service.SeckillService;
import com.ybs.seckil.util.IpUtil;
import com.ybs.seckil.util.UUIDUtil;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CourseRedis courseRedis;

    @Autowired
    private SeckillRedis seckillRedis;

    private Map<String, Boolean> isSecill = new HashMap<>();

    @Override
    public Result<Orders> seckillFlow(User user, String courseNo) {
        // 判断库存
//        Course course = courseService.findCourseByCourseNo(courseNo);
        Boolean isPass = isSecill.get(courseNo);
        if (isPass){
            // 没有库存了
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }

        // 使用redis直接-1 预减库存
        double stockQuantity = courseRedis.incr(courseNo, 1);

        if (stockQuantity <= 0){
            isSecill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }

       /* int stock = course.getStockQuantity();
        if (stock <= 0){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }*/
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

    @Override
    public void cacheAllCourse() {
        List<Course> courseList = courseService.findAllCourse();
        if(courseList == null){
            return;
        }
        for(Course course : courseList){
            courseRedis.putString(course.getCourseNo(), course.getStockQuantity(), 60, true);
            courseRedis.put(course.getCourseNo(), course, -1);
            isSecill.put(course.getCourseNo(), false);
        }
    }

    @Override
    public String getPath(User user, String courseNo) {
        String path = UUIDUtil.getUUID();
        seckillRedis.putString("path_"+courseNo+"_"+user.getUsername(), path, 60);
        return path;
    }

    @Override
    public Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request) {
        // ip 验证
        String ip = IpUtil.getIpAddr(request);
        try {
            if (seckillRedis.incr(ip, 1) >= 5){
                seckillRedis.decr(ip, 3);
                return Result.failure(ResultCode.SECKILL_IP_OUTMAX);
            }
        }finally {
        }

        String redidPath = (String) seckillRedis.getString("path_" + courseNo + "_" + user.getUsername());
        if (!path.equals(redidPath)){
            return Result.failure(ResultCode.SECKILL_PATH_ERROR);
        }
        Boolean isPass = isSecill.get(courseNo);
        if (isPass){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        double stockQuantity = courseRedis.decr(courseNo, -1);
        if (stockQuantity <= 0){
            isSecill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }

        Orders orders = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if (orders != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }

        kafkaTemplate.send("test",courseNo+","+user.getUsername());
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }

}
