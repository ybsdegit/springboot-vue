package com.ybs.seckil.service.impl;

import com.ybs.seckil.model.User;
import com.ybs.seckil.repository.UserRepository;
import com.ybs.seckil.service.UserService;
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

    @Override
    public User register(User user) {

        User save = userRepository.saveAndFlush(user);
        return save;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
