package com.ybs.blog.service;

import com.ybs.blog.pojo.Type;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface TypeService {

    /**
     * 保存
     * @param type
     */
    void save(Type type);

    List<Type> getAll();

    List<Type> getTypeList();

    /**
     * 更新
     * @param type
     */
    void update(Type type);

    /**
     * 根据ID启动
     * @param id
     */
    void enableById(Integer id);
}
