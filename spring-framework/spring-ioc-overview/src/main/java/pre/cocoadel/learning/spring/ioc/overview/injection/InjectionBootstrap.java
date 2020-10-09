package pre.cocoadel.learning.spring.ioc.overview.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;
import pre.cocoadel.learning.spring.ioc.overview.domain.UserRepository;

/**
 * 依赖注入的相关演示demo
 */
public class InjectionBootstrap {

    public static void main(String[] args) {
        String location = "classpath:META-INF/dependency-injection-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);

        getBean(beanFactory);

        getBeanLazy(beanFactory);

        getIocBuiltInNOBeanInstance(beanFactory);

        getIoCBuiltInBean((beanFactory));

    }

    /**
     * 获取自定义Bean-即是用户注册的Bean
     */
    private static void getBean(BeanFactory beanFactory){
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.printf("获取自定义UserRepository Bean：%s\n", userRepository);
    }

    private static void getBeanLazy(BeanFactory beanFactory) {
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.printf("\n延迟获取注入的自定义User Bean：%s\n", userObjectFactory.getObject());
    }


    /**
     * 获取Spring IoC内建的非Bean对象
     * BeanFactory对象本身就不是Bean，它是Spring IoC内部的一个非Bean对象
     */
    private static void getIocBuiltInNOBeanInstance(BeanFactory beanFactory){
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        //Spring IoC内建的非Bean对象可以通过依赖注入来获取
        //该BeanFactory通过autowire 注入到userRepository Bean
        BeanFactory bFactory = userRepository.getBeanFactory();
        System.out.println("\n通过依赖注入获取的BeanFactory对象：" + bFactory);

        //这里bFactory和beanFactory实际上不是同一个对象
        //bFactory是BeanFactory的底层实现对象；beanFactory实际上ApplicationContext，通过组合模式调用bFactory方式来实现BeanFactory的接口
        //本质都是由bFactory对象负责底层IoC容器工作
        System.out.println("beanFactory == bFactory ： " + (beanFactory == bFactory));

        //无法通过依赖查找获取Spring IoC内建的非Bean对象
        try {
            bFactory = beanFactory.getBean(BeanFactory.class);
        } catch (Exception e){
            System.out.println("无法通过依赖查找获取BeanFactory对象：" + e.getMessage());
        }
    }

    /**
     *  获取Spring IoC 内建Bean
     *  内建Bean 依赖查找和依赖注入都可以获取
     */
    private static void getIoCBuiltInBean(BeanFactory beanFactory){
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println("\n通过依赖注入获取 内建Bean：" + userRepository.getEnvironment());

        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("通过依赖查找的方式获取 内建Bean：" + environment.toString());
        //依赖查找和依赖注入都可以获取
        System.out.printf("(userRepository.getEnvironment() == beanFactory.getBean(Environment.class): %s)\n",
                (userRepository.getEnvironment() == environment));
    }
}
