package com.g3.user.exception.customException;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("Usuário não encontrado");
    }
}
