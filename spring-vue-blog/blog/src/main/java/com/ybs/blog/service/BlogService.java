package com.ybs.blog.service;

import com.ybs.blog.pojo.Blog;
import com.ybs.blog.utils.Page;
import com.ybs.blog.vo.BlogVo;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
public interface BlogService {
    /**
     * 保存
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 更新
     * @param blog
     */
    void update(Blog blog);

    /**
     * 根据Id阅读
     * @param id
     * @return
     */
    BlogVo readById(String id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);
}
