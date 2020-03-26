package com.ybs.blog.service.impl;


import com.ybs.blog.mapper.LogMapper;
import com.ybs.blog.pojo.Log;
import com.ybs.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 保存
     * @param logger
     */
    @Override
    public void save(Log logger) {
        logMapper.save(logger);
}
}
