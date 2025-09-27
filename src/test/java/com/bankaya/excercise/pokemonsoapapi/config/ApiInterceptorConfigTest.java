package com.bankaya.excercise.pokemonsoapapi.config;

import com.bankaya.excercise.pokemonsoapapi.domain.dto.BinnacleMessage;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.BinnaclePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiInterceptorConfigTest {

    @Mock
    private BinnaclePort binnaclePort;

    @Mock
    private MessageContext messageContext;

    @Mock
    private SoapMessage soapMessage;

    @Mock
    private Source source;

    @Mock
    private javax.xml.transform.Transformer transformer;

    private ApiInterceptorConfig interceptor;

    @BeforeEach
    void setUp() {
        interceptor = new ApiInterceptorConfig(binnaclePort);

        // Configurar MockHttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        request.addHeader("X-Forwarded-For", "192.168.1.1");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void handleResponse_ShouldProcessResponseCorrectly() throws Exception {
        // Arrange
        when(messageContext.getResponse()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(source);

        // Act
        boolean result = interceptor.handleResponse(messageContext, null);

        // Assert
        assertTrue(result);
        verify(messageContext).getResponse();
    }

    @Test
    void handleFault_ShouldReturnFalse() throws Exception {
        // Act
        boolean result = interceptor.handleFault(messageContext, null);

        // Assert
        assertTrue(result);
    }

    @Test
    void afterCompletion_ShouldSaveBinnacleLog() throws Exception {
        // Arrange
        when(messageContext.getRequest()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(source);

        interceptor.handleRequest(messageContext, null);

        // Act
        interceptor.afterCompletion(messageContext, null, null);

        // Assert
        verify(binnaclePort).saveBinnacleLog(any(BinnacleMessage.class));
    }

    @Test
    void afterCompletion_WithException_ShouldHandleErrorCorrectly() throws Exception {
        // Arrange
        Exception testException = new RuntimeException("Test error");
        when(messageContext.getRequest()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(source);

        interceptor.handleRequest(messageContext, null);

        // Act
        interceptor.afterCompletion(messageContext, null, testException);

        // Assert
        verify(binnaclePort).saveBinnacleLog(any(BinnacleMessage.class));
    }

    @Test
    void getRemoteAddress_ShouldReturnForwardedIpIfAvailable() throws Exception {
        // Arrange
        when(messageContext.getRequest()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(source);
        String expectedIp = "192.168.1.1";

        // Act
        interceptor.handleRequest(messageContext, null);
        interceptor.afterCompletion(messageContext, null, null);

        // Assert
        verify(binnaclePort).saveBinnacleLog(argThat(message -> {
            assertNotNull(message);
            assertEquals(expectedIp, message.getOriginIp());
            assertNotNull(message.getRequestDate());
            return true;
        }));
    }

    @Test
    void getExecutedMethod_ShouldReturnLocalName() throws Exception {
        // Arrange
        var domSource = mock(DOMSource.class);
        var nodeMock = mock(Element.class);
        when(domSource.getNode()).thenReturn(nodeMock);
        when(messageContext.getRequest()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(domSource);
        String expectedMethod = "getPokemon";
        when(nodeMock.getLocalName()).thenReturn(expectedMethod);

        // Act
        interceptor.handleRequest(messageContext, null);
        interceptor.afterCompletion(messageContext, null, null);

        // Assert
        verify(binnaclePort).saveBinnacleLog(argThat(message -> {
            assertNotNull(message);
            assertEquals(expectedMethod, message.getExecutedMethod());
            assertNotNull(message.getRequestDate());
            return true;
        }));
    }

    @Test
    void getExecutedMethod_ShouldReturnUnknown() throws Exception {
        // Arrange
        var domSource = mock(DOMSource.class);
        when(messageContext.getRequest()).thenReturn(soapMessage);
        when(soapMessage.getPayloadSource()).thenReturn(domSource);
        String expectedMethod = "Unknown";

        // Act
        interceptor.handleRequest(messageContext, null);
        interceptor.afterCompletion(messageContext, null, null);

        // Assert
        verify(binnaclePort).saveBinnacleLog(argThat(message -> {
            assertNotNull(message);
            assertEquals(expectedMethod, message.getExecutedMethod());
            assertNotNull(message.getRequestDate());
            return true;
        }));
    }

}