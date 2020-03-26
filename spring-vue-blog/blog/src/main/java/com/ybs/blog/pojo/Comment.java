package com.ybs.blog.pojo;

import lombok.Data;
//import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * <p>
 * 评论表实体类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -262115810554538289L;

    /**
     * 评论id
     */
//    @Id
    private String id;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评价人
     */
    private Integer commentUser;

    /**
     * 评价人
     */
    private User user;

    /**
     * 评论帖子id
     */
    private String commentBlog;

    /**
     * 评价帖子
     */
    private Blog blog;

    /**
     * 点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String createdTime;

    /**
     * 是否评论，存库时，不存这个字段
     * 当查询用户评论情况时，对这个字段进行赋值
     */
    private boolean commentFlag = false;
}
