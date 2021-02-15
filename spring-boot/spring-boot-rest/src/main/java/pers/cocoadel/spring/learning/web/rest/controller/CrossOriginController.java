package pers.cocoadel.spring.learning.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrossOriginController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
