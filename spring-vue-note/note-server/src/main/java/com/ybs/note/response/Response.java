package com.ybs.note.response;

import lombok.Data;

import javax.annotation.Resource;

/**
 * Response
 *
 * @author Paulson
 * @date 2020/3/22 22:08
 */

@Data
public class Response {
    private int status;
    private String msg;
    private Object data;

    public Response(int status, String msg, Object data){
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
}
