package com.playerdatatracking.exceptions.apikeys;

public class ApiFootballRequestException extends Exception {

	 // Constructor que acepta un mensaje
    public ApiFootballRequestException(String message) {
        super(message);
    }
    
    // Constructor que acepta un mensaje y una causa
    public ApiFootballRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
