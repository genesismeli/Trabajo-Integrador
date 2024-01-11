package com.dh.apiDentalClinic.security.jwt;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}

