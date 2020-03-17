package com.ybs.seckil.kafka;

import com.ybs.seckil.model.Course;
import com.ybs.seckil.model.Orders;
import com.ybs.seckil.model.User;
import com.ybs.seckil.service.CourseService;
import com.ybs.seckil.service.OrderService;
import com.ybs.seckil.service.SeckillService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * KafkaConsumer
 *
 * @author Paulson
 * @date 2020/3/17 22:57
 */
@Component
public class KafkaConsumer {

    @Autowired
    public CourseService courseService;

    @Autowired
    public OrderService orderService;

    @Autowired
    public SeckillService seckillService;

    @KafkaListener(id="seconds-kill", topics = "test", groupId = "seconds-kill")
    public void listener(ConsumerRecord<?, ?> record) {
        String[] messages = record.value().toString().split(",");
        String courseNo  = messages[0];
        String username  = messages[1];

        Course course = courseService.findCourseByCourseNo(courseNo);
        int stock = course.getStockQuantity();
        if(stock <= 0){
            return ;
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(username, courseNo);
        if(order != null){
            return ;
        }
        //减库存 下订单
        User user = new User();
        user.setUsername(username);
        seckillService.seckill(user, course);
    }
}