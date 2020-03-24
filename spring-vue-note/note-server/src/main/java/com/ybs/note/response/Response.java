package com.ybs.note.response;

import lombok.Data;

/**
 * Response
 *
 * @author Paulson
 * @date 2020/3/22 22:08
 */

@Data
public class Response <T> {

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 400;

    private static final String MSG_SUCCESS="成功";
    private static final String MSG_FAIL="失败";

    private int status;
    private String msg;
    private T data;

    public Response(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Response(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public Response(String msg){
        this.status = 200;
        this.msg = msg;
    }

    public Response(T data){
        this.status = 200;
        this.msg = msg;
    }

    public static Response success(){
        return new Response(CODE_SUCCESS,MSG_SUCCESS);
    }

    public static Response success(Object data){
        return new Response(CODE_SUCCESS,MSG_SUCCESS, data);
    }

    public static Response success(String message){
        return new Response(CODE_SUCCESS,MSG_SUCCESS, message);
    }

    public static Response fail(){
        return new Response(CODE_FAIL, MSG_FAIL);
    }

    public static Response fail(String message) {
        return new Response(CODE_FAIL, message);
    }
}
