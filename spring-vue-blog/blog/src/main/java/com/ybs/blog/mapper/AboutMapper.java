package com.ybs.blog.mapper;

import com.ybs.blog.pojo.About;
import com.ybs.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AboutMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:33
 */
@Component
public interface AboutMapper {
    /**
     * 保存
     * @param about
     */
    void save(About about);

    /**
     * 更新
     * @param about
     */
    void updateById(About about);

    /**
     * id
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 查询
     * @return
     */
    About getAbout();

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新启用状态
     * @param about
     */
    void updateEnable(About about);

    /**
     * 分页
     * @param page
     * @return
     */
    List<About> getByPage(Page<About> page);

    /**
     * 数
     * @param page
     * @return
     */
    int getCountByPage(Page<About> page);
}
