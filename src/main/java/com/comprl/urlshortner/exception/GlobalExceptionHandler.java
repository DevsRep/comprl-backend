package com.comprl.urlshortner.exception;


import com.comprl.urlshortner.model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<APIError> handleConflictException(ConflictException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new APIError(
                                HttpStatus.CONFLICT,
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(FirebaseServiceException.class)
    public ResponseEntity<APIError> handleFirebaseServiceException(FirebaseServiceException ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        new APIError(
                                HttpStatus.SERVICE_UNAVAILABLE,
                                ex.getMessage()
                        )
                );
    }

}
