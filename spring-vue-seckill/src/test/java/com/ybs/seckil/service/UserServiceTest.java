package com.ybs.seckil.service;

import com.ybs.seckil.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserServiceTest
 *
 * @author Paulson
 * @date 2020/3/12 19:27
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = new User();
        user.setId(0);
        user.setUsername("ybsde");
        user.setPassword("mima");
        userService.register(user);
        Assert.assertNotNull(user);
    }
}