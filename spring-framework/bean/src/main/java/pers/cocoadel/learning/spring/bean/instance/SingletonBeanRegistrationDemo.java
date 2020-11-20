package pers.cocoadel.learning.spring.bean.instance;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

/**
 * 外部单例加载Bean
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        SingletonBeanRegistry singletonBeanRegistry = context.getBeanFactory();
        User user = new User();
        user.setName("Black Belladonna");
        user.setAge(14);
        user.setDescription("I am from singletonBeanRegistry");
        singletonBeanRegistry.registerSingleton("blackBelladonna", user);
        context.refresh();

        User black = context.getBean(User.class);
        System.out.println("User Bean: " + black);
        Object obj = context.getBean("component-scan");
        System.out.println(obj);
        context.close();
    }
}
