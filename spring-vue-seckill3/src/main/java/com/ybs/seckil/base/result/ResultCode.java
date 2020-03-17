package com.ybs.seckil.base.result;

import lombok.Data;

/**
 * ResultCode
 *
 * @author Paulson
 * @date 2020/3/13 16:56
 */

public enum ResultCode {

    SUCCESS(200, "成功!"),
    FAIL(500, "失败!"),
    USER_LOGIN_SUCCESS(200, "登录成功!"),
    USER_LOGIN_ERROR(500201, "登录失败，请重新输入用户名或密码!"),
    USER_REGISTER_SUCCESS(200, "注册成功!"),
    USER_REGISTER_ERROR(50022, "该用户已存在!"),

    // 秒课错误: 500100 - 500199
    SECKILL_LINE_UP(500100, "排队中"),
    SECKILL_NO_QUOTE(500101, "非常抱歉该课程名额已满，请联系小姐姐。"),
    SECKILL_BOUGHT(500102, "你已购买该课程，请去订单页面查看。"),
    SECKILL_PATH_ERROR(500104, "你的请求地址不正确，请联系小姐姐。"),
    SECKILL_IP_OUTMAX(500105, "你的请求过于频繁，请稍后再试。"),
    ;
    private Integer code;
    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
