package per.cocoadel.learning.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @EventListener 的简单运用
 */
public class AnnotatedEventListenerBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //把MyEventListener注册为Spring Bean
        context.register(MyEventListener.class);
        context.refresh();
        context.close();
    }

    public static abstract class AbstractEventListener{
        //@EventListener可以标注在抽象类中
        @EventListener(ContextRefreshedEvent.class)
        public void onContextRefreshedEvent(ContextRefreshedEvent event){
            System.out.println("AbstractEventListener : " + event.getClass().getSimpleName());
        }
    }

     public static  class  MyEventListener extends AbstractEventListener{
        //@EventListener可以是非Void的方法
        @EventListener(ContextClosedEvent.class)
        public boolean onContextClosedEvent(ContextClosedEvent event){
            System.out.println("MyEventListener: " + event.getClass().getSimpleName());
            return true;
        }
     }
}
