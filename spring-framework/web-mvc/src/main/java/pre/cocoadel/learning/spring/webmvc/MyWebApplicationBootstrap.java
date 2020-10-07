package pre.cocoadel.learning.spring.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackageClasses = MyWebApplicationBootstrap.class)
@Configuration
public class MyWebApplicationBootstrap {

    public static void main(String[] args) {
        
    }
}
