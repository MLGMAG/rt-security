package com.webmuffins.rtsx.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webmuffins.rtsx.security.exception.InvalidTokenException;
import com.webmuffins.rtsx.security.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAspect extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidTokenException(InvalidTokenException e) {
        return e.getMessage();
    }

}
