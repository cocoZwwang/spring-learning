package pers.cocoadel.learning.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class LookupEnvironmentDemo implements ApplicationContextAware {
    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LookupEnvironmentDemo.class);
        //直接带class参数的构造方法，不需要再调用refresh方法
//        context.refresh();
        LookupEnvironmentDemo demo = context.getBean(LookupEnvironmentDemo.class);
        //通过名称的方式进行依赖查找
        Environment environment2 = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(demo.environment == environment2);
        context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.environment = applicationContext.getEnvironment();
    }
}
