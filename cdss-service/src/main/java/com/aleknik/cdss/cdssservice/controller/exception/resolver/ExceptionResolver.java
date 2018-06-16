package com.aleknik.cdss.cdssservice.controller.exception.resolver;


import com.aleknik.cdss.cdssservice.controller.exception.AuthorizationException;
import com.aleknik.cdss.cdssservice.controller.exception.BadRequestException;
import com.aleknik.cdss.cdssservice.controller.exception.ForbiddenException;
import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.PatternSyntaxException;

/**
 * Catches exceptions that occur in any layer of the application (domain, service, controller).
 * This allows us to not have to worry about exception handling, as they will be caught here.
 * It also allows us to simply throw a custom exception when necessary, instead of having to propagate it up.
 * <p>
 * Caught exceptions get mapped to an adequate {@link HttpStatus}, which is then returned to the client along with the
 * exception message.
 */
@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity authorizationException(AuthorizationException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity forbiddenException(ForbiddenException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity classCastException(ClassCastException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity usernameNotFoundException(UsernameNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException() {
        return new ResponseEntity<>(new ErrorResponse("Validation failed"), HttpStatus.BAD_REQUEST);
    }
}