package pers.cocoadel.spring.learning.templateengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloWorldController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "hello ruby！");
        return "index";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello-world";
    }

    @ModelAttribute("message")
    public String message() {
        return "hello world!";
    }
}
