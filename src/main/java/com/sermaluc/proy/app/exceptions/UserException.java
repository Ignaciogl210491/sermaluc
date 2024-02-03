package com.sermaluc.proy.app.exceptions;

import org.springframework.http.HttpStatus;


public class UserException extends  Exception{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public UserException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
