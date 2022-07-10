package com.cpswork.backend.exceptions;

/**
 * @author: ntwari egide
 * @description: email is not valid exception
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailIsNotValidException extends RuntimeException{
  public EmailIsNotValidException(String message){
    super(message);
  }
}
