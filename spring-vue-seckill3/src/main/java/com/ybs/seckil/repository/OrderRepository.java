package com.ybs.seckil.repository;

import com.ybs.seckil.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 *
 * @author Paulson
 * @date 2020/3/17 21:26
 */
public interface OrderRepository extends JpaRepository<Orders, Long> {
    public Orders findByUsernameAndCourseNo(String username, String courseNo);
}
