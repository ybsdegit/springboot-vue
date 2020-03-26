package com.ybs.blog.service;

import com.ybs.blog.pojo.Link;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface LinkService {

    /**
     * 保存
     * @param link
     */
    void save(Link link);

    /**
     * 修改
     * @param link
     */
    void update(Link link);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Link> getAll();

    /**
     * 根基id删除
     * @param id
     */
    void deleteById(Integer id);
}
