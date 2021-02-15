package pers.cocoadel.spring.learning.templateengine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//切面通知
@ControllerAdvice(assignableTypes = HelloWorldController.class)
public class HelloControllerAdvice {

    @ModelAttribute("acceptLanguage")
    public String acceptLanguage(@RequestHeader(value = "Accept-Language",required = false) String acceptLanguage) {
        return acceptLanguage;
    }

    @ModelAttribute("sessionId")
    public String sessionId(@CookieValue(value = "JSESSIONID",required = false) String sessionId) {
        return sessionId;
    }

    @ModelAttribute("message")
    public String message() {
        return "hello cocoadel !";
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exceptionHandle(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage());
    }
}
