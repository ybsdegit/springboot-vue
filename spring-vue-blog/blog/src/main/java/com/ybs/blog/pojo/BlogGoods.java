package com.ybs.blog.pojo;

import lombok.Data;
//import org.springframework.data.annotation.Id;

/**
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class BlogGoods {

//    @Id
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 博客id
     */
    private String blogId;

}
