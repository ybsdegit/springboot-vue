package com.ybs.seckil.redis;

import com.ybs.seckil.model.Course;
import org.springframework.stereotype.Repository;

/**
 * UserRedis
 *
 * @author Paulson
 * @date 2020/3/13 1:13
 */

@Repository
public class CourseRedis extends BaseRedis<Course> {

    private static final String REDIS_KEY = "com.ybs.seckil.redis.CourseRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
