package com.studyjava.asyncmethod.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Value("${name}")
    String name;

    @Value("${version}")
    String version;

    @Value("#{environment.getActiveProfiles()}")
    String profiles;

    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        map.put("version", this.version);
        map.put("profiles", this.profiles);
        map.put("date", Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return ResponseEntity.ok(map);
    }
}
