package com.playerdatatracking.exceptions;

public class NotCreatedJsonFileResponse extends Exception{

	
    // Constructor que acepta un mensaje
    public NotCreatedJsonFileResponse(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public NotCreatedJsonFileResponse(String message, Throwable cause) {
        super(message, cause);
    }
}
