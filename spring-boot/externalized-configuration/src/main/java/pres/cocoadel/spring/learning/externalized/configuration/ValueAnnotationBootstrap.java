package pres.cocoadel.spring.learning.externalized.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import pres.cocoadel.spring.learning.externalized.configuration.domain.User;

@EnableAutoConfiguration
public class ValueAnnotationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean("user",User.class);
        System.out.println("user: " + user);

        User user2 = context.getBean("user2",User.class);
        System.out.println("user2: " + user2);

        context.close();

    }

    @Bean
    public User user(@Value("${user.id}") long id,
                     @Value("${user.name}") String name,
                     @Value("${user.age:32}") int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Bean
    public User user2(Environment environment) {
        User user = new User();
        long id = environment.getProperty("user.id",Long.class,0L);
        String name = environment.getProperty("user.name",String.class);
        int age = environment.getProperty("user.age",Integer.class,
                environment.getProperty("user.my.age",Integer.class,19));
        user.setId(id);
        user.setAge(age);
        user.setName(name);
        return user;
    }
}
