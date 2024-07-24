package com.playerdatatracking.exceptions;

public class PlayerInputException extends Exception{
	
    // Constructor que acepta un mensaje
    public PlayerInputException(String e) {
        super(e);
    }

    // Constructor que acepta un mensaje y una causa
    public PlayerInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
