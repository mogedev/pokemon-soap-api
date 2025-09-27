package com.bankaya.excercise.pokemonsoapapi.config;

import com.bankaya.excercise.pokemonsoapapi.domain.dto.BinnacleMessage;
import com.bankaya.excercise.pokemonsoapapi.ports.spi.BinnaclePort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApiInterceptorConfig implements EndpointInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ApiInterceptorConfig.class);
    private final ThreadLocal<Instant> startTime = new ThreadLocal<>();
    private final BinnacleMessage binnacleMessage = new BinnacleMessage();

    private final BinnaclePort binnaclePort;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        startTime.set(Instant.now());
        LOG.info("Processing SOAP Request...");
        String requestXml = messageToString(messageContext.getRequest());
        binnacleMessage.setRequest(requestXml);
        binnacleMessage.setRequestDate(LocalDateTime.now());
        binnacleMessage.setOriginIp(getRemoteAddress());
        binnacleMessage.setExecutedMethod(getExecutedMethod(messageContext));
        LOG.info("Request SOAP: {}", requestXml);
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Processing SOAP Response...");
        String responseXml = messageToString(messageContext.getResponse());
        binnacleMessage.setResponse(responseXml);
        LOG.info("Response SOAP: {}", responseXml);
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Processing SOAP Error Response...");
        String responseXml = messageToString(messageContext.getResponse());
        binnacleMessage.setResponse(responseXml);
        LOG.info("Response SOAP: {}", responseXml);
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        Duration duration = Duration.between(startTime.get(), Instant.now());
        binnacleMessage.setDurationTime(duration.toMillis());
        LOG.info("Total processing time: {} ms", duration.toMillis());
        LOG.info("Binnacle Message: {}", binnacleMessage);
        binnaclePort.saveBinnacleLog(binnacleMessage);
        if (ex != null) {
            LOG.error("Error during processing: ", ex);
        }
        startTime.remove();
    }

    private String getRemoteAddress() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String remoteAddr = request.getRemoteAddr();
            // Intentar obtener IP real si está detrás de un proxy
            String forwardedFor = request.getHeader("X-Forwarded-For");
            return forwardedFor != null ? forwardedFor : remoteAddr;
        }

        return "Unknown";
    }

    private String messageToString(WebServiceMessage message) {
        try {
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory.newInstance().newTransformer().transform(message.getPayloadSource(), result);
            return writer.toString()
                    .replaceAll(">[\\s\\r\\n]+<", "><")
                    .replaceAll("[\\r\\n]+", "")
                    .trim();
        } catch (Exception e) {
            LOG.error("Error al convertir mensaje a string", e);
            return "Error al procesar mensaje";
        }
    }

    private String getExecutedMethod(MessageContext messageContext) {
        WebServiceMessage request = messageContext.getRequest();
        Source source = request.getPayloadSource();
        if (source instanceof DOMSource) {
            Node node = ((DOMSource) source).getNode();
            if (node instanceof Element) {
                return ((Element) node).getLocalName();
            }
        }
        return "Unknown";
    }

}
