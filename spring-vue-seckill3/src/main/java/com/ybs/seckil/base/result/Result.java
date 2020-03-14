package com.ybs.seckil.base.result;

import lombok.Data;

/**
 * Reuslt
 *
 * @author Paulson
 * @date 2020/3/13 16:51
 */

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> failure(){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static <T> Result<T> failure(T data){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }



    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(ResultCode resultCode){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> success(ResultCode resultCode, T data){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

}
