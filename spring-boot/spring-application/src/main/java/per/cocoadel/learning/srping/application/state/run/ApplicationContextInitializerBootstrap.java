package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ApplicationContextInitializerBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationContextInitializerBootstrap.class)
                //重复注册HelloWorldApplicationContextInitializer
                //这里因为HelloWorldApplicationContextInitializer没有重写hashCode和equals方法，所以两个相同类型ApplicationContextInitializer的子对象不会去重
                //同理通过META-INF/spring.factories也一样不会去重
                //所以使用Spring 工厂加载机制的场景，配置的实现类最好都重写hashCode和equals方法
//                .initializers(new HelloWorldApplicationContextInitializer(),new HelloWorldApplicationContextInitializer())
                .run(args)
                .close();
    }
}
