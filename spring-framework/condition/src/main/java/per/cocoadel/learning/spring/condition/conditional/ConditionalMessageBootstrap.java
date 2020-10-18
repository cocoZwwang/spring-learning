package per.cocoadel.learning.spring.condition.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *  自定义Conditional例子
 */
@ComponentScan(basePackageClasses = ConditionalMessageBootstrap.class)
public class ConditionalMessageBootstrap {

    static {
        System.setProperty("language","Chinese");
//        System.setProperty("language","English");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionalMessageBootstrap.class);
        String message = context.getBean(String.class);
        System.out.println("message: " + message);
    }
}
