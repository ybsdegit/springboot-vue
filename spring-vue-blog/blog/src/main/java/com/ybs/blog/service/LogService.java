package com.ybs.blog.service;

import com.ybs.blog.pojo.Log;
import com.ybs.blog.utils.Page;

import java.util.List;

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

    /**
     * 分页
     * @param page
     * @return
     */
    Page<Log> getByPage(Page<Log> page);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);
}
