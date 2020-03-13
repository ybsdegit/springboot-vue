package com.ybs.seckil.service;

import com.ybs.seckil.model.User;
import com.ybs.seckil.redis.UserRedis;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.interfaces.PBEKey;
import javax.sound.midi.Soundbank;

import java.util.List;

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

    @Autowired
    private UserRedis userRedis;

    @Test
    public void test(){
        User user = new User();
        user.setId(0);
        user.setUsername("ybsde");
        user.setPassword("mima");
        userService.register(user);
        Assert.assertNotNull(user);
    }


    @Test
    public void testRedis(){
        User user = new User();
//        user.setId(0);
        user.setUsername("ybsde2");
        user.setPassword("mima");
        userRedis.put(user.getUsername(), user, -1);
    }

    @Test
    public void testRedis2(){
        List<User> all = userRedis.getAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void testUserRedis(){
        User user = userService.getUser("ybs");
        System.out.println(user);
    }
}