package ru.alex_tsoy.calculator.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alex_tsoy.calculator.dto.response.CustomExceptionResponse;
import ru.alex_tsoy.calculator.exceptions.RootNotFoundException;

@RestControllerAdvice
@Slf4j
public class DefaultRestExceptionHandler {

    @ExceptionHandler(RootNotFoundException.class)
    public ResponseEntity<CustomExceptionResponse> handleRootNotFound(RootNotFoundException ex) {
        String message = ex.getMessage();
        log.error(message);
        log.trace(message, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handle(ex));
    }

    private CustomExceptionResponse handle(Exception ex) {
        return new CustomExceptionResponse(ex.getMessage());
    }
}
