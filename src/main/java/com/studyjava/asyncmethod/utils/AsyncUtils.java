package com.studyjava.asyncmethod.utils;

import com.studyjava.asyncmethod.dtos.GenericRespDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Log4j2
public class AsyncUtils {

    private AsyncUtils() {}

    public static Map<String, GenericRespDto> callAndReturn(List<Callable<Object>> taskList)
    {
        Map<String, GenericRespDto> genericResults = new HashMap<>();

        //executa tarefas assync
        //ExecutorService executorService = Executors.newFixedThreadPool(taskList.size());

        //Permitindo cache de threads
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Criando um ExecutorService
        try
        {
            // Invocando todas as tarefas simultaneamente
            List<Future<Object>> futures = executorService.invokeAll(taskList);

            // Esperando até que todas as tarefas estejam concluídas
            for (Future<Object> future : futures) {
                Future<GenericRespDto> preResult = (Future<GenericRespDto>) future.get();
                GenericRespDto result = preResult.get();
                genericResults.put(result.getRequestName(), result);
            }
        } catch (InterruptedException | ExecutionException e) {
            genericResults.putAll(createExceptionRespose("async_calling_erro", e));
            log.warn("Failled to run assync {} ", e.getMessage());
            Thread.currentThread().interrupt();
        }finally {
            Thread.interrupted();
            executorService.shutdown();
        }

        return genericResults;
    }

    public static Map<String, GenericRespDto> createExceptionRespose(String callName, Exception body)
    {
        //executa chamada e retorna resultado
        Map<String, GenericRespDto> results = new HashMap<>();
        GenericRespDto errorRespose = new GenericRespDto();
        errorRespose.setStatus(HttpStatus.BAD_REQUEST);
        errorRespose.setResponseBody(body);
        errorRespose.setResponseType(Exception.class);
        results.put(callName, errorRespose);
        return results;
    }
}
