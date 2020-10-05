package per.cocoadel.learning.spring.boot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;

import java.util.Random;

public class MultipleSpringBootEventsListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationReadyEvent.class.equals(eventType) || ApplicationFailedEvent.class.equals(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return SpringApplication.class.equals(sourceType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationReadyEvent){
            if(new Random().nextBoolean()){
                throw new RuntimeException("ApplicationReadyEvent 监听异常！");
            }
        }
        System.out.println("MultipleSpringBootEventListener 监听到事件：" + event.getClass().getSimpleName());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
