package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;

public class UncaughtExceptionHandlerBootstrap {
    public static void main(String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            System.out.printf("【处理线程：%s】的非捕获异常：%s\n",t.getName(),e.getMessage());
        });
        new SpringApplicationBuilder(UncaughtExceptionHandlerBootstrap.class)
                .listeners(event -> {
                    //需要添加这个前缀（Logback configuration error detected）
                    //SpringBootExceptionHandler才会向用户设置的UncaughtExceptionHandler对象传递异常
                    //一般用户不需要设置UncaughtExceptionHandler对象，只要扩展spring 自带的异常分析报告机制既可以
                    if(event instanceof ApplicationReadyEvent){
                        throw new RuntimeException("Logback configuration error detected:故意抛出的异常，" +
                                "测试设置的UncaughtExceptionHandler是否被调用");
                    }
                })
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
