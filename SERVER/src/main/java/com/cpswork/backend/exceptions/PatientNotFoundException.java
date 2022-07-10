package com.cpswork.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String invalid_credentials) {
        super(invalid_credentials);
    }
}
