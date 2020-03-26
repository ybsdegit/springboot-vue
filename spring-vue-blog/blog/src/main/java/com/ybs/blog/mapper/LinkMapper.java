package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Link;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * LinkMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:33
 */

@Component
public interface LinkMapper {
    /**
     * 保存
     * @param link 友链
     */
    void save(Link link);

    /**
     * 修改
     * @param link
     */
    void update(Link link);

    /**
     * 根据Id查询
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
     * 根据Id杀出
     * @param id
     */
    void deleteById(Integer id);
}
