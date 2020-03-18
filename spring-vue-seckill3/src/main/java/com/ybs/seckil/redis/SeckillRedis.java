package com.ybs.seckil.redis;

import com.ybs.seckil.model.Orders;
import org.springframework.stereotype.Repository;

/**
 * SeckillRedis
 *
 * @author Paulson
 * @date 2020/3/18 1:50
 */

@Repository
public class SeckillRedis extends BaseRedis<Orders> {
    private static final String REDIS_KEY = "com.ybs.seckil.redis.SeckillRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
