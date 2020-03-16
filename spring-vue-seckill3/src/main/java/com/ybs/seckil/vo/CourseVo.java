package com.ybs.seckil.vo;

import com.ybs.seckil.model.Course;
import lombok.Data;

import java.io.Serializable;

/**
 * CourseVo
 *
 * @author Paulson
 * @date 2020/3/15 1:42
 */

@Data
public class CourseVo implements Serializable {
    private Course course;
    private int courseStatus = 0;
    private int remainTime = 0;
}
