package com.g3.user.exception.customException;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException(){
        super("Usuário não encontrado");
    }
}
