package com.ybs.blog.service;

import com.ybs.blog.pojo.Log;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);
}
