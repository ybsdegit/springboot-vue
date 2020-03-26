package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Blog;
import com.ybs.blog.utils.Page;
import com.ybs.blog.vo.BlogVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BlogMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:32
 */
@Component
public interface BlogMapper {
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
     * 删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlogVo> page);
}
