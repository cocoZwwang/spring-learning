package pers.cocoadel.spring.learning.web.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    @CrossOrigin("*")
    public String hello() {
        return "hello rest!";
    }
}
