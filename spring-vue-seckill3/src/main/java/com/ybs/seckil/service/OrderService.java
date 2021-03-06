package com.ybs.seckil.service;

import com.ybs.seckil.model.Orders;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * OrderService
 *
 * @author Paulson
 * @date 2020/3/17 21:23
 */
public interface OrderService {

    Orders getOrderByUsernameAndCourseNo(String username, String courseNo);

    Orders saveAndFlush(Orders orders);

    List<Orders> findAllByUsername(String username, Sort sort);
}
