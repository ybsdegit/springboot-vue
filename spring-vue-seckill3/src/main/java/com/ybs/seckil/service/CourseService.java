package com.ybs.seckil.service;

import com.ybs.seckil.model.Course;
import java.util.List;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/12 19:21
 */
public interface CourseService {
    public List<Course> findAllCourse();

    public Course finCourseByCourseNo(String courseNo);
}
