package com.myblog12.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String message) {


        super(message);
    }
}
