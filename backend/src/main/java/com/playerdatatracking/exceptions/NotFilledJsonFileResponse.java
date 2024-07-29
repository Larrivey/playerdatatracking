package com.playerdatatracking.exceptions;

public class NotFilledJsonFileResponse extends Exception{

	
    // Constructor que acepta un mensaje
    public NotFilledJsonFileResponse(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public NotFilledJsonFileResponse(String message, Throwable cause) {
        super(message, cause);
    }
}
