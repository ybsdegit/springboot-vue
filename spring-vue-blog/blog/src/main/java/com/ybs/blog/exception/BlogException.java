package com.ybs.blog.exception;

import com.ybs.blog.enums.ResultEnum;
import lombok.Data;

/**
 * BaseException
 *
 * @author Paulson
 * @date 2020/3/25 23:06
 */

@Data
public class BlogException extends RuntimeException {

    private static final long serialVersionUid = 234234234234234234L;
    private Integer errorCode = ResultEnum.ERROR.getCode();

    public BlogException() {
    }

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BlogException(String msg){
        super(msg);
    }

    protected BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
