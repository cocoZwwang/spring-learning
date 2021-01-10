package pers.cocoadel.learning.spring.environment;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        String location = "classpath:META-INF/environment-resolver-context.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);
        User user = context.getBean(User.class);
        System.out.println(user.toString());
        context.close();
    }
}
