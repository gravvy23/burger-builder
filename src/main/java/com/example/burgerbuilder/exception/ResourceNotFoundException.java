package com.example.burgerbuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class representing Not Found Exception
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Creates object with provided message
     * @param message message displayed on page
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates object with provided message and cause
     * @param message message displayed on page
     * @param cause cause of the error
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}