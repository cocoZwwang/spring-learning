package per.cocoadel.learning.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

public class AnnotatedAsyncEventListenerBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册MyAsyncEventListener为Spring Bean
        context.register(MyAsyncEventListener.class);
        context.refresh();
        context.close();
    }

    //激活异步
    @EnableAsync
    public static class MyAsyncEventListener{

        @EventListener(ContextRefreshedEvent.class)
        @Async
        //异步监听，返回值不能是原生类型，所以不能是boolean
        public Boolean onAsyncEvent(ContextRefreshedEvent event) {
            print("onAsyncEvent: " + event.getClass().getSimpleName());
            return true;
        }

        private void print(String message) {
            System.out.printf("[thread:%s] - %s",Thread.currentThread().getName(),message);
        }
    }
}
