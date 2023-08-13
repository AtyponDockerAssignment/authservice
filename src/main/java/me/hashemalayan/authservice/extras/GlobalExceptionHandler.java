package me.hashemalayan.authservice.extras;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // Here, you can parse the exception message to be more specific about which constraint was violated.
        // For this example, I'm just checking for a unique constraint violation regarding the 'email' field.
        if (ex.getMessage().contains("email")) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("A database error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more handlers for other exceptions if needed
}
