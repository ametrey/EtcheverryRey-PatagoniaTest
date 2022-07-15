package com.itpatagonia.microservices.subjectservice.Exceptions;

public class NoEntityException extends Exception{
    public NoEntityException(String message){
        super(message);
    }
}
