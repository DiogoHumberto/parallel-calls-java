package com.studyjava.asyncmethod.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.GeneralSecurityException;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate setUp(RestTemplateBuilder restTemplateBuilder) throws GeneralSecurityException {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        // Configure o RestTemplate para usar o DefaultResponseErrorHandler
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());

        return restTemplate;
    }

    private SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // Defina um timeout de conexão e leitura, se necessário
        factory.setConnectTimeout(10000);
        // factory.setReadTimeout(5000);

        return factory;
    }

}
