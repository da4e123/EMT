package org.example.lab1.model.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(){
        super("Invalid user credentials exception");
    }

}
