package per.cocoadel.learning.spring.condition.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalMessageConfiguration {


    @Bean
    @ConditionalOnSystemProperty(name = "language",value = "Chinese")
    public String messageChinese(){
        return "你好啊！朋友";
    }

    @Bean
    @ConditionalOnSystemProperty(name = "language",value = "English")
    public String messageEnglish(){
        return "Hi! friend";
    }
}

