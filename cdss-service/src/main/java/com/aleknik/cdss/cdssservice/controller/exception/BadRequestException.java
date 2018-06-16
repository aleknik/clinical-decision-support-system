package com.aleknik.cdss.cdssservice.controller.exception;

/**
 * Custom exception.
 * Gets mapped to {@link org.springframework.http.HttpStatus#BAD_REQUEST} when caught in
 * {@link com.code10.security.controller.exception.resolver.ExceptionResolver}.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}