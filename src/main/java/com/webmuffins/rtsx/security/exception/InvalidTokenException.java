package com.webmuffins.rtsx.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }

}
