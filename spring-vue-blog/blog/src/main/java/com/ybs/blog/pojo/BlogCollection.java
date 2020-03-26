package com.ybs.blog.pojo;

import lombok.Data;
//import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * <p>
 * 收藏时间实体类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class BlogCollection implements Serializable {

    private static final long serialVersionUID = -535915810554536111L;

    /**
     * 收藏id
     */
//    @Id
    private String collectionId;

    /**
     * 帖子id
     */
    private String blogId;

    private Blog blog;

    /**
     * 用户id
     */
    private Integer userId;

    private User user;

    /**
     * 收藏时间
     */
    private String collectionTime;

}
