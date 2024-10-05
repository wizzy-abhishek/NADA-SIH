package com.ai.aiml10.advices;

import com.ai.aiml10.exceptions.DuplicateIdException;
import com.ai.aiml10.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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

        apiError.setStatusCode(HttpStatus.NOT_FOUND);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<APIError> duplicateResource(DuplicateIdException exception){

        APIError apiError = new APIError();

        apiError.setStatusCode(HttpStatus.BAD_REQUEST);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST) ;

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleUltimateException(Exception exception){

        APIError apiError = new APIError();

        apiError.setStatusCode(HttpStatus.BAD_REQUEST);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleMethodArgumentInvalid(MethodArgumentNotValidException exception){

        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        APIError apiError = new APIError();

        apiError.setStatusCode(HttpStatus.BAD_REQUEST);
        apiError.setError("INPUT VALIDATION FAILED");
        apiError.setSubErrors(errors);

        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<APIError> handleAuthenticationException(AuthenticationException ex) {
        APIError apiError = new APIError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<APIError> handleExpiredJwtException(ExpiredJwtException expiredJwtException){
        APIError apiError = new APIError(expiredJwtException.getMessage() , HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(apiError , HttpStatus.UNAUTHORIZED);
    }

   @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<APIError> handleTemperedJWT
           (MalformedJwtException malformedJwtException){
        APIError apiError = new APIError(malformedJwtException.getMessage() , HttpStatus.UNAUTHORIZED);
        apiError.getSubErrors().add("WARNING THEIR MIGHT BE TAMPERING IN REQUEST");

        return new ResponseEntity<>(apiError , HttpStatus.UNAUTHORIZED);
   }


}
