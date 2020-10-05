package per.cocoadel.learning.spring.boot.event;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.support.AbstractApplicationContext;

public class MultipleSpringBootEventsListenerBootstrap {
    public static void main(String[] args) {
        /**
         * MultipleSpringBootEventsListener作为引导类源，被注册到ConfigurableApplicationContext中去
         * {@link AbstractApplicationContext#registerListeners()}
         * 方法会关联所有实现了ApplicationListener的Spring Bean 到ConfigurableApplicationContext应用上下文
         */
        new SpringApplicationBuilder(MultipleSpringBootEventsListener.class)
                .web(false)
                .run(args)
                .close();
    }
}
