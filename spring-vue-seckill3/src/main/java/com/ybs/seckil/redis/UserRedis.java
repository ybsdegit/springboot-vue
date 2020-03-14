package com.ybs.seckil.redis;

import com.ybs.seckil.model.User;
import org.springframework.stereotype.Repository;

/**
 * UserRedis
 *
 * @author Paulson
 * @date 2020/3/13 1:13
 */

@Repository
public class UserRedis extends BaseRedis<User> {

    private static final String REDIS_KEY = "com.ybs.seckil.redis.UserRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
