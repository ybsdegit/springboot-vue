package com.ybs.note.service;

import com.ybs.note.mapper.UserMapper;
import com.ybs.note.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/22 20:33
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username){
        return userMapper.findByUsername(username);
    }

    public boolean isExistUser(String username){
        User user = getUserByUsername(username);
        return user!=null;
    }

    public void addUser(User user){
        userMapper.save(user);
    }
}
