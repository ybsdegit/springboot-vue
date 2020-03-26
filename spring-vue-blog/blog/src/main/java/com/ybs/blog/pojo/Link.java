package com.ybs.blog.pojo;

import lombok.Data;

/**
 * 友情链接
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class Link {

    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;

}
