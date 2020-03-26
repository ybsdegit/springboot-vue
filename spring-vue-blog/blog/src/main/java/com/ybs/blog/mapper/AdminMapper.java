package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * AdminMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:31
 */
@Component
public interface AdminMapper {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);
}
