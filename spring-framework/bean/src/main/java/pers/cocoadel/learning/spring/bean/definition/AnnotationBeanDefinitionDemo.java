package pers.cocoadel.learning.spring.bean.definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

/**
 * 通过注解的方式
 */
@ComponentScan(basePackageClasses = AnnotationBeanDefinitionDemo.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationBeanDefinitionDemo.class);
        User user = context.getBean(User.class);
        System.out.println("user bean: " + user);

        UserRuby ruby = context.getBean(UserRuby.class);
        System.out.println("UserRuby bean: " + ruby);
        context.close();
    }

    /**
     *  通过@Bean方式装配Bean
     */
    @Bean
    public User user(){
        User user = new User();
        user.setName("Weiss");
        user.setAge(14);
        return user;
    }

    @Component
    public static class UserRuby {
        private User user;

        public UserRuby() {
            this.user = new User();
            user.setName("Ruby Rose");
            user.setAge(15);
        }

        //实际上如果Spring应用上下文中存在User Bean，那么就算没有@Autowired，Spring也会自动推断使用带User参数的构造方法。
        @Autowired
        public UserRuby(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "UserRuby{" +
                    "user=" + user +
                    '}';
        }
    }
}
