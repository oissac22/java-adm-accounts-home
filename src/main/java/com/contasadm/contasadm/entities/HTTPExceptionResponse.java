package com.contasadm.contasadm.entities;

import java.io.Serializable;

import com.contasadm.contasadm.exceptions.HTTPException;

public class HTTPExceptionResponse implements Serializable {
    private int status = 200;
    private String msg = "";

    public HTTPExceptionResponse (HTTPException e) {
        this.status = e.getStatus();
        this.msg = e.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
    
}