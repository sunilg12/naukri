package com.naukri.database_api.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message){
        super(message);
    }
}
