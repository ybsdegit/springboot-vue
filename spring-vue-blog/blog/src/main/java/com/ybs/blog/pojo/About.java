package com.ybs.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class About implements Serializable {

    private Integer aboutId;
    private String aboutTitle;
    private String aboutContent;
    private Integer aboutRead;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer enable;
    private Integer deleted;

}
