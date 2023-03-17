package com.example.bbpos.cipher.exceptions;

public class InvalidKeyLengthException extends Exception {
    public InvalidKeyLengthException() {
        super("Invalid key length");
    }
}