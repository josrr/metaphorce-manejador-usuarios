package com.jmarr.users.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.ArrayList;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jmarr.users.services.UserAlreadyExistsException;
import com.jmarr.users.services.ResourceNotFoundException;
import com.jmarr.users.controllers.response.ErrorResponse;

import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request)
    {
        ErrorResponse err = new ErrorResponse("No fue posible leer la petición",
                                              Collections.singletonList(e.getMessage()),
                                              LocalDateTime.now());
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RuntimeException.class })
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException e, WebRequest request)
    {
        ErrorResponse err = new ErrorResponse(e.getMessage(), Collections.emptyList(), LocalDateTime.now());
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public final ResponseEntity<Object> handleResourceNotFoundException(RuntimeException e, WebRequest request)
    {
        ErrorResponse err = new ErrorResponse(e.getMessage(), Collections.emptyList(), LocalDateTime.now());
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public final ResponseEntity<Object> handleAccessDeniedExceptionException(AccessDeniedException e,
                                                                             WebRequest request)
    {
        ErrorResponse err = new ErrorResponse(e.getMessage(), Collections.emptyList(), LocalDateTime.now());
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ UserAlreadyExistsException.class })
    public final ResponseEntity<Object> handleUserAlreadyExistsException(Exception e, WebRequest request)
    {
        ErrorResponse err = new ErrorResponse(e.getMessage(), Collections.emptyList(), LocalDateTime.now());
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
