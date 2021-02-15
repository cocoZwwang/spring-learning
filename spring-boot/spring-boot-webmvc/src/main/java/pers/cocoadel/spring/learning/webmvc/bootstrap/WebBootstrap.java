package pers.cocoadel.spring.learning.webmvc.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "pers.cocoadel.spring.learning.webmvc")
public class WebBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(WebBootstrap.class, args);
    }
}
