package com.ybs.blog.vo;

import com.ybs.blog.pojo.Type;
import lombok.Data;

import java.io.Serializable;

/**
 * BlogVo
 *
 * @author Paulson
 * @date 2020/3/26 19:04
 */

@Data
public class BlogVo implements Serializable {
    /**
     * 帖子id
     */
    private String blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 封面
     */
    private String blogImage;

    /**
     * 帖子内容
     */
    private String blogContent;

    /**
     * 点赞数
     */
    private Integer blogGoods;

    /**
     * 阅读数
     */
    private Integer blogRead;

    /**
     * 收藏数
     */
    private Integer blogCollection;

    /**
     * 博客分类
     */
    private String typeName;

    /**
     * 简介
     */
    private String blogRemark;

    /**
     * 评论数
     */
    private Integer blogComment;

    /**
     * 文章来源（爬虫时使用）
     */
    private String blogSource;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;


}
