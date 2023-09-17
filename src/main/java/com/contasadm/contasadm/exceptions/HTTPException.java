package com.contasadm.contasadm.exceptions;

public class HTTPException extends RuntimeException {
    private int status = 200;

    public HTTPException(String msg, int status) {
        super(msg);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + status;
        return result;
    }
}
