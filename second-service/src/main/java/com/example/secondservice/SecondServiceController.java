package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
public class SecondServiceController {

    @GetMapping("/welcome")
    private String welcome() {
        return "Welcome to Second Service";
    }

    @GetMapping("/message")
    private String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return header;
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is message from Second Service.";
    }
}
