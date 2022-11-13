package com.ozgekatirci.TwitterClone.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final HttpStatus status;
    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status= status;
    }
}

