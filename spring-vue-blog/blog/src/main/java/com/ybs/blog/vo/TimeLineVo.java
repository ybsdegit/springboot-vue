package com.ybs.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * TimeLineVo
 * 时间轴
 * @author Paulson
 * @date 2020/4/27 12:01
 */

@Data
public class TimeLineVo implements Serializable {

    private String month;

    private List<BlogVo> list;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeLineVo that = (TimeLineVo) o;
        return month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
}
