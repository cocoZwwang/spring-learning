package pers.cocoadel.spring.learning.web.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.cocoadel.spring.learning.web.rest.domain.User;

@RestController
public class UserController {

    @PostMapping(value = "/echo/user",
            consumes = "application/*;charset=UTF-8",
            produces = "application/json;charset=UTF-8"
            )
    public User echoUser(@RequestBody User user) {
        return user;
    }
}
