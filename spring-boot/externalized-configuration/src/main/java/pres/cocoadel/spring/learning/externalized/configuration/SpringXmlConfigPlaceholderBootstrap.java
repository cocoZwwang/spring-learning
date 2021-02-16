package pres.cocoadel.spring.learning.externalized.configuration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pres.cocoadel.spring.learning.externalized.configuration.domain.User;

public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {
        String[] locations = new String[]{"/META-INF/spring/spring-context.xml",
                "/META-INF/spring/user-context.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);

        User user = context.getBean(User.class);

        System.out.println(user);

        context.close();
    }
}
