package per.cocoadel.learning.spring.annotation.enable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @Enable 模式注解例子
 */
@EnableServer(type = Server.Type.Ftp)
public class EnableServerBootstrap {
    public static void main(String[] args) {
        //注册Spring应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册引导类
        context.register(EnableServerBootstrap.class);
        //刷选应用上下文
        context.refresh();
        //查找Server Bean 并且调用start() 方法
        Server server = context.getBean(Server.class);
        server.start();
        //关闭spring 应用上下文
        context.close();
    }
}

