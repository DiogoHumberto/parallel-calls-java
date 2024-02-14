package com.studyjava.asyncmethod.controllers;

import com.studyjava.asyncmethod.dtos.ApiRequestDto;
import com.studyjava.asyncmethod.services.ParallelCallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParallelCallController {

    private final ParallelCallService service;
    @PostMapping("/get-all")
    public ResponseEntity<?> getForAllCalls(@RequestBody @Valid ApiRequestDto reqDto){

        return ResponseEntity.ok(service.executeParellelCall(reqDto));
    }
}
