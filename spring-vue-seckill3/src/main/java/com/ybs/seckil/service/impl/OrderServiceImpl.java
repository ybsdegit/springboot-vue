package com.ybs.seckil.service.impl;

import com.ybs.seckil.model.Orders;
import com.ybs.seckil.repository.OrderRepository;
import com.ybs.seckil.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * OrderServiceImpl
 *
 * @author Paulson
 * @date 2020/3/17 21:23
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders getOrderByUsernameAndCourseNo(String username, String courseNo) {
        return orderRepository.findByUsernameAndCourseNo(username, courseNo);
    }

    @Override
    public Orders saveAndFlush(Orders orders) {
        return orderRepository.saveAndFlush(orders);
    }
}
