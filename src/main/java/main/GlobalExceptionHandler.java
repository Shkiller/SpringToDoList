package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public final ResponseEntity handleException(Exception ex, WebRequest request) {
        if (ex instanceof EntityNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            EntityNotFoundException enfe = (EntityNotFoundException) ex;
            return handleEntityNotFoundException(enfe, status, request);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity handleEntityNotFoundException(EntityNotFoundException enfe, HttpStatus status, WebRequest request) {
       // System.out.println(enfe.getMessage());
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
