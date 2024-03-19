package com.socials.Gateway.exception;

public class TokenMissingException extends RuntimeException{

    public TokenMissingException(String message){
        super(message);
    }
}
