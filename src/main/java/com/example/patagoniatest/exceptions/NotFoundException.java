package com.example.patagoniatest.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id) {
        super("not found with Id: " + id);
    }

}
