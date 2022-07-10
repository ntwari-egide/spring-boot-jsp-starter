/**
 * @author: ntwari egide
 * @description: user not found modified exception handler
 */

package com.cpswork.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String invalid_credentials) {
        super(invalid_credentials);
    }
}
