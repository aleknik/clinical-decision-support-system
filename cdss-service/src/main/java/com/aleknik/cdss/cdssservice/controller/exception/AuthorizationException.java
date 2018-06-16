package com.aleknik.cdss.cdssservice.controller.exception;

/**
 * Custom exception.
 * Gets mapped to {@link org.springframework.http.HttpStatus#UNAUTHORIZED} when caught in
 * {@link com.code10.security.controller.exception.resolver.ExceptionResolver}.
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }
}