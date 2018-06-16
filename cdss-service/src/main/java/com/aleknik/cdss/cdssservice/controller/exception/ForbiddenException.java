package com.aleknik.cdss.cdssservice.controller.exception;

/**
 * Custom exception.
 * Gets mapped to {@link org.springframework.http.HttpStatus#FORBIDDEN} when caught in
 * {@link com.code10.security.controller.exception.resolver.ExceptionResolver}.
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}