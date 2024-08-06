package com.playerdatatracking.exceptions;

public class SecretKeyBadGeneratedException extends Exception {

	
    // Constructor que acepta un mensaje
    public SecretKeyBadGeneratedException(String e) {
        super(e);
    }

    // Constructor que acepta un mensaje y una causa
    public SecretKeyBadGeneratedException(String message, Throwable cause) {
        super(message, cause);
    }
}
