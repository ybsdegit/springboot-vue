package com.ybs.seckil.util;

import com.ybs.seckil.model.Course;
import com.ybs.seckil.service.CourseService;
import com.ybs.seckil.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CourseUtil
 *
 * @author Paulson
 * @date 2020/3/15 1:53
 */
public class CourseUtil {

    public static final int COURSE_NOT_START = 0; // 还没开始
    public static final int COURSE_PROCESSING = 1; // 正在进行中
    public static final int COURSE_COMPLETE = 2;  // 已结束


    public static CourseVo courseToCourseVo(Course course){
        CourseVo courseVo = new CourseVo();
        courseVo.setCourse(course);

        // 课程状态
        // 剩余时间

        int courseStatus = 0;
        int remainTime = 0;

        long startTime = course.getStartTime().getTime();
        long endTime = course.getEndTime().getTime();
        long nowTime = System.currentTimeMillis();

        if (nowTime < startTime){
            courseStatus = COURSE_NOT_START;
            remainTime = (int) ((startTime - nowTime)/1000);
        }else if (nowTime > endTime){
            courseStatus = COURSE_COMPLETE;
            remainTime = -1;
        }else {
            courseStatus = COURSE_PROCESSING;
            remainTime = 0;
        }
        courseVo.setCourseStatus(courseStatus);
        courseVo.setRemainTime(remainTime);
        return courseVo;
    }

}
