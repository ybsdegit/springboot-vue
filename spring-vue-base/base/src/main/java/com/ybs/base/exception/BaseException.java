package com.ybs.base.exception;

import com.ybs.base.enums.ResultEnum;
import lombok.Data;

/**
 * BaseException
 *
 * @author Paulson
 * @date 2020/3/25 23:06
 */

@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUid = 234234234234234234L;
    private Integer errorCode = ResultEnum.ERROR.getCode();

    public BaseException() {
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BaseException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BaseException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BaseException(String msg){
        super(msg);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
