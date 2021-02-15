package pre.cocoadel.learning.spring.ioc.overview.injection;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.util.List;

@ComponentScan(basePackageClasses = AutowiredDemo.class)
public class AutowiredDemo {

    @Autowired
    UserRepository userRepository;

    //如果是属性注入 required 为 false 的时候，User Bean 不存在的时候不会报错，属性为 null
    @Autowired(required = false)
    User user;

    @Autowired(required = false)
    private List<User> users;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AutowiredDemo.class);
        context.refresh();
        AutowiredDemo demo = context.getBean(AutowiredDemo.class);
        System.out.println(demo.userRepository.getUser());
        List<User> list = demo.users;
        System.out.println("users size: " + (list == null ? 0 : list.size()));

        context.close();
    }

    @Data
    @Component
    public static class UserRepository {

        private User user;

        //Autowired required 为 false 的是时候，如果是构造函数注入
        //如果 User Bean 不存在，会抛出 NoSuchBeanDefinitionException
        //所以如果是构造，最好使用 ObjectProvider
//        @Autowired(required = false)
//        public UserRepository(User user) {
//            this.user = user;
//        }

        //Autowired required 为 false 的是时候，如果是方法注入也不会报错
        @Autowired(required = false)
        public void setUser(User user) {
            this.user = user;
        }
    }
}
