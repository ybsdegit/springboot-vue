package com.ybs.seckil.base.result;

import lombok.Data;

/**
 * ResultCode
 *
 * @author Paulson
 * @date 2020/3/13 16:56
 */

public enum ResultCode {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    USER_LOGIN_ERROR(500201, "登录失败，请重新输入用户名或密码"),
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
