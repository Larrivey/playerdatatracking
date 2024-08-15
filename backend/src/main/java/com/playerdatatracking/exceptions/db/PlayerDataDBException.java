package com.playerdatatracking.exceptions.db;

public class PlayerDataDBException extends Exception {
    // Constructor que acepta un mensaje
    public PlayerDataDBException(String e) {
        super(e);
    }

    // Constructor que acepta un mensaje y una causa
    public PlayerDataDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
