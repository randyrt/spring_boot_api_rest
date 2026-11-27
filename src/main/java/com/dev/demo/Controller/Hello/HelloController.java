package com.dev.demo.Controller.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
            "message", "Hello, Spring Boot + MariaDB is up!",
            "status", "ok"
        );
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
            "message", "Hello depuis le controller !",
            "langage", "Java best language",
            "framework", "Spring Boot"
        );
    }
}
