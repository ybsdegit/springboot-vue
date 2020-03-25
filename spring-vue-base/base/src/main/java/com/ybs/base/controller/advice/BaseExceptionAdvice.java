package com.ybs.base.controller.advice;

import com.ybs.base.exception.BaseException;
import com.ybs.base.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BaseExceptionAdvice
 * 定义统一异常处理
 * @author Paulson
 * @date 2020/3/25 23:16
 */

@ControllerAdvice
@Slf4j
public class BaseExceptionAdvice {

    /**
     * 统一处理
     * @param exception
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BaseException exception){
//        exception.printStackTrace();
        log.error("统一异常处理", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }


}
