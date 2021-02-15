package pers.cocoadel.spring.learning.web.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class PropertiesRestController {

//    @PostMapping(value = "/add/props",
//            consumes = "text/properties;charset=UTF-8",
//            produces = "text/plain")
//    public String addProps(@RequestBody Properties properties) {
//        System.out.println(properties.toString());
//        return "success!";
//    }

//    @PostMapping(value = "/add/props",
//            consumes = "text/properties;charset=UTF-8")
//    public Properties addProps(@RequestBody Properties properties) {
//        return properties;
//    }

    @PostMapping(value = "/add/props",
            consumes = "text/properties;charset=UTF-8")
    public Properties addProps(Properties properties) {
        return properties;
    }
}
