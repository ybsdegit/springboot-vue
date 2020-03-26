package com.ybs.blog.advice;

import com.ybs.blog.exception.BlogException;
import com.ybs.blog.utils.Result;
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
public class BlogExceptionAdvice {

    /**
     * 统一处理
     * @param exception
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception){
        log.error("统一异常处理", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
}
