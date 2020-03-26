package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Log;
import com.ybs.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * LogMapper
 *
 * @author Paulson
 * @date 2020/3/26 0:32
 */
@Component
public interface LogMapper {
    /**
     * 保存
     * @param logger
     */
    public void save(Log logger);

    /**
     * 分页
     * @param page
     * @return
     */
    List<Log> getByPage(Page<Log> page);

    /**
     * 数
     * @param page
     * @return
     */
    int getCountByPage(Page<Log> page);

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
