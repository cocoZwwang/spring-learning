package pers.cocoadel.learning.spring.bean.creation;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pre.cocoadel.learning.spring.ioc.overview.domain.Cat;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.rmi.registry.Registry;
import java.util.Map;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:META-INF/bean-definiton-creation.xml");
        //通过Java API注册
        createBeanByApiWithStaticMethod(context);
        createBeanApiWithFactoryBeanStaticMethod(context);
        context.refresh();

        //通过静态方法，实例化对象
        User user = context.getBean("user-by-static-method",User.class);
        System.out.println("xml user-by-static-method: " + user);

        //通过Java API （注册BeanDefinition + 静态方法实例化）
        User userByJavaApiStaticMethod = context.getBean("user-by-java-api-static-method", User.class);
        System.out.println("java api user-by-java-api-static-method: " + userByJavaApiStaticMethod);

        //通过FactoryBean + 静态方法实例化对象
        User userByFactoryBeanStaticMethod = context.getBean("user-by-factory-bean-static-method", User.class);
        System.out.println("user-by-factory-bean-static-method: " + userByFactoryBeanStaticMethod);

        //通过Java API （注册BeanDefinition + FactoryBean静态方法实例化对象）
        User userByJavaApiFactoryBeanStaticMethod = context.getBean("user-by-java-api-factorybean-static-method", User.class);
        System.out.println("user-by-java-api-factorybean-static-method: " + userByJavaApiFactoryBeanStaticMethod);

        //通过FactoryBean实现创建的User对象并且装载成Spring Bean
        //user bean 的 beanName是xml中注册FactoryBean的名称
        //而FactoryBean本身的BeanName则会在注册名称开头添加&：&+UserBeanName
        User userByFactoryBean = context.getBean("user-by-factory-bean", User.class);
        System.out.println("user-by-factory-bean: " + userByFactoryBean);

        Map<String, FactoryBean> factoryBeans = context.getBeansOfType(FactoryBean.class);
        factoryBeans.forEach((name,u) -> System.out.printf("[bean name:%s] - %s\n",name,u));
        context.close();
    }

    /**
     * 通过Java API （注册BeanDefinition + 静态方法实例化）
     */
    private static void createBeanByApiWithStaticMethod(BeanDefinitionRegistry registry) {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .setFactoryMethod("createUser")
                //这里的属性最终会覆盖createUser方法中的赋值，因为在Spring Bean的生命周期中，Property的处理会在实例化之后。
                .addPropertyValue("name", "Weiss")
                .getBeanDefinition();
        registry.registerBeanDefinition("user-by-java-api-static-method", beanDefinition);
    }

    /**
     * 通过Java API （注册BeanDefinition + FactoryBean静态方法实例化对象）
     */
    private static void createBeanApiWithFactoryBeanStaticMethod(BeanDefinitionRegistry registry){
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                //第一个参数：静态方法；第二个参数：FactoryBean BeanName
                .setFactoryMethodOnBean("createUser","user-factory-bean")
                .addPropertyValue("name","Black Belladonna")
                .getBeanDefinition();
        registry.registerBeanDefinition("user-by-java-api-factorybean-static-method", beanDefinition);
    }
}
