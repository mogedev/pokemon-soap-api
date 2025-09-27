package com.bankaya.excercise.pokemonsoapapi.component;

import com.bankaya.excercise.pokemonsoapapi.domain.exception.PokemonNotFoundException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PokeApiErrorDecoderTest {
    private final PokeApiErrorDecoder decoder = new PokeApiErrorDecoder();

    private Response buildResponse(int status, String body) {
        return Response.builder()
                .status(status)
                .reason("reason")
                .headers(Collections.emptyMap())
                .request(Request.create(Request.HttpMethod.GET, "/test", Collections.emptyMap(), null, StandardCharsets.UTF_8, null))
                .body(body, StandardCharsets.UTF_8)
                .build();
    }

    @Test
    void decode_BadRequest_ShouldReturnIllegalArgumentException() {
        Response response = buildResponse(HttpStatus.BAD_REQUEST.value(), "bad request body");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(IllegalArgumentException.class, ex);
        assertTrue(ex.getMessage().contains("bad request body"));
    }

    @Test
    void decode_Unauthorized_ShouldReturnSecurityException() {
        Response response = buildResponse(HttpStatus.UNAUTHORIZED.value(), "unauthorized");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(SecurityException.class, ex);
    }

    @Test
    void decode_Forbidden_ShouldReturnAccessDeniedException() {
        Response response = buildResponse(HttpStatus.FORBIDDEN.value(), "forbidden");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(AccessDeniedException.class, ex);
    }

    @Test
    void decode_NotFound_ShouldReturnPokemonNotFoundException() {
        Response response = buildResponse(HttpStatus.NOT_FOUND.value(), "not found");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(PokemonNotFoundException.class, ex);
    }

    @Test
    void decode_InternalServerError_ShouldReturnRuntimeException() {
        Response response = buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal error");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(RuntimeException.class, ex);
    }

    @Test
    void decode_ServiceUnavailable_ShouldReturnRetryableException() {
        Response response = buildResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), "service unavailable");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(RetryableException.class, ex);
    }

    @Test
    void decode_UnknownStatus_ShouldReturnGenericException() {
        Response response = buildResponse(418, "I'm a teapot");
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(Exception.class, ex);
        assertFalse(ex instanceof IllegalArgumentException);
        assertFalse(ex instanceof SecurityException);
        assertFalse(ex instanceof java.nio.file.AccessDeniedException);
        assertFalse(ex instanceof PokemonNotFoundException);
        assertFalse(ex instanceof RuntimeException);
        assertTrue(ex.getMessage().contains("I'm a teapot"));
    }

    @Test
    void whenResponseBodyIsNull_ShouldReturnNoResponseBodyMessage() {
        Response response = buildResponse(418, null);
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(Exception.class, ex);
        assertTrue(ex.getMessage().contains("No response body"));
    }

    @Test
    void whenInputStreamFromBody_ShouldReturnErrorMessage() throws IOException {
        var responseBodyMock = Mockito.mock(Response.Body.class);
        when(responseBodyMock.asInputStream()).thenThrow(new IOException("Stream error"));
        Response response = Response.builder()
                .status(418)
                .reason("reason")
                .headers(Collections.emptyMap())
                .request(Request.create(Request.HttpMethod.GET, "/test", Collections.emptyMap(), null, StandardCharsets.UTF_8, null))
                .body(responseBodyMock)
                .build();
        Exception ex = decoder.decode("method", response);
        assertInstanceOf(Exception.class, ex);
        assertTrue(ex.getMessage().contains("Error reading response body"));
    }


}