package pers.cocoadel.learning.spring.bean.definition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 通过Spring API 直接注册BeanDefinition
 * {@link BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)}
 */
public class ApiBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册User BeanDefinition
        //通过BeanDefinitionBuilder构造BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("name", "ruby")
                .addPropertyValue("age", 15);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        BeanDefinitionRegistry beanDefinitionRegistry = context;
        //通过BeanDefinitionRegistry注册BeanDefinition
        //第一个参数：beanName
        beanDefinitionRegistry.registerBeanDefinition("userRuby", beanDefinition);

        //通过GenericBeanDefinition构造BeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
        argumentValues.addIndexedArgumentValue(0, "Weiss");
        argumentValues.addIndexedArgumentValue(1, 14);
        argumentValues.addIndexedArgumentValue(2,"I am created by GenericBeanDefinition!");
        genericBeanDefinition.setConstructorArgumentValues(argumentValues);
//        genericBeanDefinition.setAttribute("name", "Weiss");
//        genericBeanDefinition.setAttribute("age", 14);
        //通过BeanDefinitionReaderUtils非命名方式注册BeanDefinition
        BeanDefinitionReaderUtils.registerWithGeneratedName(genericBeanDefinition, beanDefinitionRegistry);
        context.refresh();

        //通过依赖查找，返回所以装载User Bean
        Map<String, User> userMap = context.getBeansOfType(User.class);
        //打印BeanName和相应的Bean信息
        userMap.forEach((beanName,userBean) ->{
            System.out.printf("[BeanName:%s] - %s\n", beanName, userBean);
        });
        context.close();
    }
}
