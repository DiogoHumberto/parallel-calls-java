package com.studyjava.asyncmethod.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyjava.asyncmethod.dtos.ApiRequestDto;
import com.studyjava.asyncmethod.dtos.GenericRespDto;
import com.studyjava.asyncmethod.utils.ClientRequestHttp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.studyjava.asyncmethod.utils.AsyncUtils.callAndReturn;

@Service
@RequiredArgsConstructor
public class ParallelCallService {

    private final ClientRequestHttp clientHttp;


    public Object executeParellelCall(ApiRequestDto reqDto){

        List<Callable<Object>> callableList = new ArrayList<>();

        reqDto.getUrls().stream().forEach(f -> callableList.add(()-> featureCall(f)));


        return callAndReturn(callableList);
    }


    @Async
    private Future<GenericRespDto> featureCall(String url) throws JsonProcessingException {

        ResponseEntity<?> resp = clientHttp.callHttp( new HttpHeaders(), null, HttpMethod.GET, url, String.class);

        return CompletableFuture.supplyAsync(() ->
                GenericRespDto.builder()
                    .requestName(url)
                    .status(resp.getStatusCode())
                    .responseBody(resp.getBody())
                    .responseType(String.class)
                    .build());
    }
}
