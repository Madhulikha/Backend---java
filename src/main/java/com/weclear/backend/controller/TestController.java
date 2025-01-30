package com.weclear.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Get all services
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }

}
