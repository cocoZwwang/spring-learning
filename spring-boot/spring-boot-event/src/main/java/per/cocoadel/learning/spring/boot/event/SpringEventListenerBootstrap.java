package per.cocoadel.learning.spring.boot.event;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;

public class SpringEventListenerBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringEventListenerBootstrap.class)
                .listeners(event -> {
                    System.out.println("Spring Application 事件监听："
                            + event.getClass().getSimpleName());
                    if(event instanceof ApplicationReadyEvent){
                        throw new RuntimeException("故意抛点异常！");
                    }
                })
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
