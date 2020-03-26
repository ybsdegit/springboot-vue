package com.ybs.blog.controller;

import com.ybs.blog.exception.BlogException;
import com.ybs.blog.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author Paulson
 * @date 2020/3/26 0:55
 */

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试异常
     * @return
     */
    @RequestMapping("/testException/{id}")
    public Result<Object> testException(@PathVariable Integer id){
       if (id == 1){
           return new Result<>();
       }else {
           throw new BlogException("发生了异常");
       }
    }


}
