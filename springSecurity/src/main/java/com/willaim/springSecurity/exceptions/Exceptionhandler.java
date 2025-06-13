package com.willaim.springSecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exceptionhandler extends ResponseEntityExceptionHandler{

@ExceptionHandler(UsernameNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
private ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }
}
