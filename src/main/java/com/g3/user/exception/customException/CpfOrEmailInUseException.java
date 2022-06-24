package com.g3.user.exception.customException;

public class CpfOrEmailInUseException extends Exception{
    public CpfOrEmailInUseException(){
        super("Usuário já cadastrado com este CPF ou Email.");
    }
}
