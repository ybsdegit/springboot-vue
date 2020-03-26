package com.ybs.blog.mapper;

import com.ybs.blog.pojo.Log;
import org.springframework.stereotype.Component;

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
}
