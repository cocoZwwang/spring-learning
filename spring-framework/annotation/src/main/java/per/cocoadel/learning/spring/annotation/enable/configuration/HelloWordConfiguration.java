package per.cocoadel.learning.spring.annotation.enable.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWordConfiguration {

    @Bean
    public String helloWord(){
        return "hello word!";
    }
}

