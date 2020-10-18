package per.cocoadel.learning.spring.annotation.enable.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableHelloWord
public class EnableHelloWordBootstrap {
    public static void main(String[] args) {
        //创建spring 应用上下文，并且注册引导类，刷选上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EnableHelloWordBootstrap.class);
        String helloWord = context.getBean("helloWord",String.class);
        System.out.println(helloWord);
        context.close();
    }
}
