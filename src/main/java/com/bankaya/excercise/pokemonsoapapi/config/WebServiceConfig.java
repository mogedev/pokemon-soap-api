package com.bankaya.excercise.pokemonsoapapi.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig implements WsConfigurer {

    private final ApiInterceptorConfig interceptor;

    public WebServiceConfig(ApiInterceptorConfig interceptor) {
        this.interceptor = interceptor;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/pokemons/*");
    }

    @Bean(name = "pokemons")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pokemonsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Pokemons");
        wsdl11Definition.setLocationUri("/pokemons");
        wsdl11Definition.setTargetNamespace("http://www.bankaya.com/pokemon-soap-api");
        wsdl11Definition.setSchema(pokemonsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema pokemonsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("pokemons.xsd"));
    }

    @Bean
    public SoapFaultAnnotationExceptionResolver exceptionResolver() {
        return new SoapFaultAnnotationExceptionResolver();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(interceptor);
    }
}

