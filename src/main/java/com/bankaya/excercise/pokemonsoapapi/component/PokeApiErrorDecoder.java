package com.bankaya.excercise.pokemonsoapapi.component;

import com.bankaya.excercise.pokemonsoapapi.domain.exception.PokemonNotFoundException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;

public class PokeApiErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(PokeApiErrorDecoder.class);

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String responseBody = extractResponseBody(response);


        // Log error details
        logger.error("Feign client error. Status: {}, Body: {}",
                status, responseBody);

        // Map status codes to exceptions
        return switch (status) {
            case BAD_REQUEST -> new IllegalArgumentException("Invalid request: " + responseBody);
            case UNAUTHORIZED -> new SecurityException("Unauthorized access");
            case FORBIDDEN -> new AccessDeniedException("Access forbidden");
            case NOT_FOUND ->
                    new PokemonNotFoundException("The requested Pokémon was not found. Please check the name.");
            case INTERNAL_SERVER_ERROR -> new RuntimeException("Internal server error");
            case SERVICE_UNAVAILABLE ->
                    new RetryableException(
                            response.status(),
                            "Service unavailable",
                            response.request().httpMethod(),
                            null,
                            0L,
                            response.request()
                    );
            default -> new Exception("Unexpected error: " + responseBody);
        };
    }

    private String extractResponseBody(Response response) {
        if (response.body() == null) {
            return "No response body";
        }

        try {
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            logger.error("Failed to read response body", ex);
            return "Error reading response body";
        }
    }
}
