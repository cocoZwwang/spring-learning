package pers.cocoadel.learning.spring.bean.definition;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pre.cocoadel.learning.spring.ioc.overview.domain.Cat;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

/**
 * 通过XML配置的方式注册Spring Bean
 */
public class XMLBeanDefinitionDemo {

    public static void main(String[] args) {
        //
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册XML配置文件
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
        String location = "classpath:META-INF/bean-definition-register.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        //刷选SpringApplicationContext
        context.refresh();
        //通过依赖查找获取User Bean对象
        User user = context.getBean(User.class);
        //打印user对象
        System.out.println("User Bean: " + user);

        Cat cat = context.getBean(Cat.class);
        System.out.println("Cat Bean: " + cat);

        //关闭spring 应用上下文
        context.close();

    }
}
