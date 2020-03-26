package com.ybs.blog.pojo;

import lombok.Data;
//import org.springframework.data.annotation.Id;

/**
 * 评论点赞
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class CommentGoods {

//    @Id
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评论id
     */
    private String commentId;

}
