package com.example.companyms.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptions {

    private Logger log = LoggerFactory.getLogger(ControllerExceptions.class);


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse er = new  ErrorResponse(HttpStatus.BAD_REQUEST.toString(),"Field Errors");
        er.setDetails(errors);
        //log.error(throwable.getMessage());
        return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundError(Throwable throwable) {
        ErrorResponse er = new  ErrorResponse(HttpStatus.BAD_REQUEST.toString(),throwable.getMessage());
        log.error(throwable.getMessage());
        return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> HttpMessageNotReadableException(Throwable throwable) {
        ErrorResponse er = new  ErrorResponse(HttpStatus.BAD_REQUEST.toString(),throwable.getMessage().split(":")[0]);
        log.error(throwable.getMessage());
        return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> runtimeError(Throwable throwable) {
        ErrorResponse er =
                new  ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        throwable.getMessage().split(":")[0]
                );
        log.error(throwable.getMessage());
        return new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public boolean missingParam(){
        return true;
    }

}
