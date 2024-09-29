package com.ai.aiml10.advices;

import com.ai.aiml10.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFound(ResourceNotFoundException exception){

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleUltimateException(Exception exception){

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleMethodArgumentInvalid(MethodArgumentNotValidException exception){

        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setError(exception.getMessage());
        apiError.setSubErrors(errors);

        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST) ;
    }
}
