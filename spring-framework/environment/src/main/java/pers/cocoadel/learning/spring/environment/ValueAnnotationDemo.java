package pers.cocoadel.learning.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValueAnnotationDemo {
    //会自动注入系统属性，当前用户名称
    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ValueAnnotationDemo.class);
        ValueAnnotationDemo demo = context.getBean(ValueAnnotationDemo.class);
        System.out.println(demo.userName);
        context.close();
    }
}
