package com.ybs.blog.config;

import com.ybs.blog.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BeanConfig
 * 用于将一些实体类放入Spring容器
 * @author Paulson
 * @date 2020/3/25 23:41
 */

@Configuration
public class BeanConfig {

    /**
     * 雪花算法 生成唯一ID
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }


}
