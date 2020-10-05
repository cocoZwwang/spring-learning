package per.cocoadel.learning.spring.boot.event;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringEventListenerBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringEventListenerBootstrap.class)
                .listeners(event -> System.out.println("Spring Application 事件监听："
                        + event.getClass().getSimpleName()))
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
