package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;

/**
 * 异常退出码的生命周期演示
 */
public class ExitCodeGeneratorExceptionBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ExitCodeGeneratorExceptionBootstrap.class)
                .web(WebApplicationType.NONE)
                .listeners(event -> {
                    System.out.println("event: " + event.getClass().getSimpleName());
                    //spring 1.5之前 第一个被调用的spring boot 事件是stared(),这个方法的异常无法生成退出码，不存在strating()方法
                    //spring 1.5之后 第一个被调用的spring boot 事件是strating(),这个方法的异常无法生成退出码，
                    // 而stared()被调整到后面触发，异常可以生成退出码
                    if(event instanceof ApplicationStartedEvent){//当前是2.0.2，1.5之前换成ApplicationStartReadyEvent
                        throw new ExitCodeGeneratorException(event.getClass().getSimpleName() + "  发送异常！");
                    }
                })
                .run(args)
                .close();
    }

    public static class ExitCodeGeneratorException extends RuntimeException implements ExitCodeGenerator{

        public  ExitCodeGeneratorException(String message){
            super(message);
        }

        @Override
        public int getExitCode() {
            return 99;
        }
    }
}


