package com.playerdatatracking.exceptions;

public class MalformedRequestException extends Exception {
    
    // Constructor que acepta un mensaje
    public MalformedRequestException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public MalformedRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}