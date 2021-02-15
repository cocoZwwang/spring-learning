package pers.cocoadel.learning.spring.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

public class LiveBeanViewDemo {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME,"pers.cocoadel.learning.spring");
        context.refresh();
        System.out.println("按任意键推出...");
        System.in.read();
        context.close();
    }
}
