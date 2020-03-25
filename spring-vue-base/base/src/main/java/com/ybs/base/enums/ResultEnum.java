package com.ybs.base.enums;

import lombok.Getter;

/**
 * ResultEnum
 * 返回结果枚举
 * @author Paulson
 * @date 2020/3/25 22:51
 */

@Getter
public enum ResultEnum {
    SUCCESS(20000, "操作成功"),
    ERROR(40000, "操作失败"),
    DATA_NOT_FOUND(40001, "查询失败！"),
    PARAMS_NULL(40002, "参数不能为空！"),

    NOT_LOGIN(40003, "当前账号未登录！"),


    ;
    /**
     * 返回结果枚举，每个枚举代表着一个返回状态
     */
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
