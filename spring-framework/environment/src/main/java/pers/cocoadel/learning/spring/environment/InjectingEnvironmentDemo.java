package pers.cocoadel.learning.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {
    private Environment environment;

    @Autowired
    private Environment environment2;

    @Autowired
    private ApplicationContext context;

    private ApplicationContext context2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectingEnvironmentDemo.class);
        //直接带class参数的构造方法，不需要再调用refresh方法
//        context.refresh();
        InjectingEnvironmentDemo demo = context.getBean(InjectingEnvironmentDemo.class);
        //直接注入的两个对象是相同的
        System.out.println(demo.environment == demo.environment2);
        //通过ApplicationContext间接注入的两个对象也是相同的
        System.out.println(demo.context == demo.context2);
        System.out.println(demo.environment == demo.context.getEnvironment());
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context2 = applicationContext;
    }
}
