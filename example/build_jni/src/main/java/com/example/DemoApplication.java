package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
public class DemoApplication {
    @Autowired
    private CallSdk callSdk;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/addr")
    public String addr() {
        String ret = "";
        for (int i =0; i < 100; i++) {
            ret = callSdk.parseAddr();
        }
        return "Hello, GET!" + ret + "end";
    }
}