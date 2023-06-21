package com.api.controller.exception;

public class ReadException extends RuntimeException{
    public ReadException(String message){
        super(message);
    }
}
