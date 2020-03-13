package com.ybs.seckil.service;

import com.ybs.seckil.model.User;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/12 19:21
 */
public interface UserService {
    public User register(User user);

    User getUser(String username);

    void saveUserToRedisByToken(User dbUser, String token);

    Object getUserFromRedisByToken(String token);
}
