package com.ybs.seckil.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SamplerController
 *
 * @author Paulson
 * @date 2020/3/12 19:02
 */

@RestController
public class SamplerController {

    @RequestMapping("/")
    public String home(){
        return "hello world";
    }
}
