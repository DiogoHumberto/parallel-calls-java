package com.studyjava.asyncmethod.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ClientRequestHttp {

    @Autowired
    private RestTemplate restTemplate;

    public <T> ResponseEntity<T> callHttp(HttpHeaders headers, Object body, HttpMethod method, String url, Class<T> responseType) throws JsonProcessingException {
        String bodyContent = body == null ? "{}" : CustomObjectMapper.writeValueAsString(body);
        log.info("---- Iniciando chamada header: {} , http: {} , url: {} , body: {} ", headers, method.name(), url, bodyContent);
        return restTemplate.exchange(url, method, new HttpEntity<>(bodyContent, headers), responseType);
    }
}
