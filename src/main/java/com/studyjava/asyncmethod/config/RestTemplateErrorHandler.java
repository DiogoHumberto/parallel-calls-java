package com.studyjava.asyncmethod.config;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import java.io.IOException;

@Log4j2
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    public void handleError(ClientHttpResponse response) throws IOException {

        // your error handling here
        log.info("Error handler foi acionado");
        log.info(response.getStatusCode());
        log.info(response.getBody());
    }
}
