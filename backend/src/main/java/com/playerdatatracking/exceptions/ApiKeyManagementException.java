package com.playerdatatracking.exceptions;

public class ApiKeyManagementException extends Exception {

	 // Constructor que acepta un mensaje
    public ApiKeyManagementException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa
    public ApiKeyManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
