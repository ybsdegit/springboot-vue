package com.ybs.blog.pojo;

import lombok.Data;

/**
 * @author Paulson
 * @date 2020/3/25 23:41
 */
@Data
public class Music {

    private Integer id;
    private String name;
    private String artist;
    private String url;
    private String cover;
    private String lrc;
    private String createdTime;
    private Integer enabled;
    private Integer deleted;

}
