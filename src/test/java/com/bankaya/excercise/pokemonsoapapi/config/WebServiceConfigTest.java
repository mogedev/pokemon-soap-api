package com.bankaya.excercise.pokemonsoapapi.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WebServiceConfigTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private ApiInterceptorConfig interceptor;

    @InjectMocks
    private WebServiceConfig config;

    @Test
    void messageDispatcherServlet_ShouldConfigureCorrectly() {
        // Act
        ServletRegistrationBean<MessageDispatcherServlet> bean =
            config.messageDispatcherServlet(applicationContext);

        // Assert
        assertNotNull(bean);
        assertEquals("/pokemons/*", bean.getUrlMappings().iterator().next());

        MessageDispatcherServlet servlet = bean.getServlet();
        assertTrue(servlet.isTransformWsdlLocations());
    }

    @Test
    void pokemonsSchema_ShouldReturnSimpleXsdSchema() {
        // Act
        XsdSchema schema = config.pokemonsSchema();

        // Assert
        assertNotNull(schema);
        assertInstanceOf(SimpleXsdSchema.class, schema);
        assertNotNull(schema.getSource());
        assertTrue(schema.getSource().getSystemId().contains("pokemons.xsd"));
    }

    @Test
    void exceptionResolver_ShouldReturnSoapFaultResolver() {
        // Act
        SoapFaultAnnotationExceptionResolver resolver = config.exceptionResolver();

        // Assert
        assertNotNull(resolver);
    }

    @Test
    void addInterceptors_ShouldAddConfiguredInterceptor() {
        // Arrange
        List<EndpointInterceptor> interceptors = new ArrayList<>();

        // Act
        config.addInterceptors(interceptors);

        // Assert
        assertEquals(1, interceptors.size());
        assertTrue(interceptors.contains(interceptor));
    }

    @Test
    void defaultWsdl11Definition_ShouldConfigureWsdlCorrectly() {
        // Arrange
        XsdSchema schema = config.pokemonsSchema();

        // Act
        var wsdlDefinition = config.defaultWsdl11Definition(schema);

        // Assert
        assertNotNull(wsdlDefinition);
    }
}