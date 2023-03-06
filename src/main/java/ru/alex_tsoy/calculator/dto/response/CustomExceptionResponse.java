package ru.alex_tsoy.calculator.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class CustomExceptionResponse {

    private final Instant time = Instant.now();

    private String message;

    public CustomExceptionResponse(String message) {
        this.message = message;
    }
}
