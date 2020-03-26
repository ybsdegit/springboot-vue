package com.ybs.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 帖子类型表实体类
 * </p>
 *
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class Type implements Serializable {

    private static final long serialVersionUID = -952315810554536348L;

    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 帖子数
     */
    private Integer typeBlogCount;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

}
