package com.g3.user.exception.customException;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
