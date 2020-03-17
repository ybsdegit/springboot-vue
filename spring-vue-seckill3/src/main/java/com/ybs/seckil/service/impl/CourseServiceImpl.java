package com.ybs.seckil.service.impl;

import com.alibaba.fastjson.JSON;
import com.ybs.seckil.model.Course;
import com.ybs.seckil.redis.CourseRedis;
import com.ybs.seckil.repository.CourseRepository;
import com.ybs.seckil.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/12 19:21
 */

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    public static final String ALL_COURSE_REDIS = "allCourseRedis";

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRedis courseRedis;




    @Override
    public List<Course> findAllCourse() {
        // 从 redis 中读取数据
        List<Course> courseList = new ArrayList<>();
        String courseListString = (String) courseRedis.getString(ALL_COURSE_REDIS);
        if (StringUtils.isEmpty(courseListString)){
            // redis 中没有数据，从数据库查，然后缓存到redis中
            courseList = courseRepository.findAll();
            courseListString = JSON.toJSONString(courseList);
            courseRedis.putString(ALL_COURSE_REDIS, courseListString, -1);
        }else {
            courseList = JSON.parseArray(courseListString, Course.class);
        }
        return courseList;
    }

    @Override
    public Course findCourseByCourseNo(String courseNo) {
        Optional<Course> course = courseRepository.findById(courseNo);
        return course.orElse(null); // course.isPresent()? user.get() : null
    }

    @Override
    public int reduceStockByCourseNo(String courseNo) {

        return courseRepository.reduceStockByCourseNo(courseNo);
    }
}
