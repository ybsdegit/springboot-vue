package com.ybs.blog.service;

import com.ybs.blog.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface AdminService {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);
}
