package com.myblog12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>excptionHandler(Exception e, WebRequest webRequest){
        return  new ResponseEntity <>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
