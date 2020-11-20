package pers.cocoadel.learning.spring.bean.init;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pers.cocoadel.learning.spring.bean.domain.Student;

/**
 * Bean初始化 演示
 */
public class BeanInitDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanInitDemo.class);
        //XML配置初始化方法
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:META-INF/bean-init.xml");

        //API方式
        AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(Student.class)
                .addPropertyValue("name","Weiss")
                .addPropertyValue("age",14)
                .getBeanDefinition();
        //指定初始化方法
        definition.setInitMethodName("init");
        context.registerBeanDefinition("WeissStudent", definition);

        //通过依赖查找触发Bean加载
        context.getBeansOfType(Student.class);
        context.close();
    }

    @Bean(initMethod = "init")
    public Student student(){
        Student student = new Student();
        student.setName("Black");
        student.setAge(14);
        return student;
    }
}
