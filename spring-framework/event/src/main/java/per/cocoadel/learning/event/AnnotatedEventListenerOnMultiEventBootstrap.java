package per.cocoadel.learning.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


/**
 * @EventListener 方法监听多个ApplicationEvent
 */
public class AnnotatedEventListenerOnMultiEventBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册MultiEventListener为Spring Bean
        context.register(MultiEventListener.class);
        context.refresh();
        context.close();
    }

    private static class MultiEventListener {
        //无参数监听多个ApplicationEvent
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onEvent() {
            System.out.println("onEvent-A");
        }

        //单个参数监听多个ApplicationEvent
        //单个参数必须是ContextRefreshedEvent和ContextClosedEvent的父类(ApplicationEvent)，否在会抛出异常
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onEvent(ApplicationEvent event){
            System.out.println("onEvent-B: " + event.getClass().getSimpleName());
        }

        //多个方法参数监听多个ApplicationEvent
        //这里会出错，因为@EventListener不支持多个方法参数
//        @EventListener({ContextRefreshedEvent.class,ContextClosedEvent.class})
//        public void onEvent(ContextRefreshedEvent event1,ContextClosedEvent event2){
//            System.out.println("onEvent-C: event1- " + event1.getClass().getSimpleName() +
//                    "  event2-" + event2.getClass().getSimpleName());
//        }


    }
}
