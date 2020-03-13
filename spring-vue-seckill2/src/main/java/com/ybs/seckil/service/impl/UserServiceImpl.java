package com.ybs.seckil.service.impl;

import com.ybs.seckil.model.User;
import com.ybs.seckil.redis.UserRedis;
import com.ybs.seckil.repository.UserRepository;
import com.ybs.seckil.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * UserServiceImpl
 *
 * @author Paulson
 * @date 2020/3/12 19:22
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedis userRedis;

    @Override
    public User register(User user) {

        User save = userRepository.saveAndFlush(user);
        return save;
    }

    @Override
    public User getUser(String username) {

        User user = userRedis.get(username);
        if (user == null){
            user = userRepository.findByUsername(username);
            if (user != null){
                userRedis.put(user.getUsername(), user, -1);
            }else {
                return null;
            }
        }
        return user;
    }

    @Override
    public void saveUserToRedisByToken(User dbUser, String token) {
        userRedis.put(token, dbUser, 3600);
    }

    @Override
    public Object getUserFromRedisByToken(String token) {
        return userRedis.get(token);
    }
}
