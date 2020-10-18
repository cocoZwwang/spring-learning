package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;

/**
 * Throwable和退出码映射接口-ExitCodeExceptionMapper的演示
 * 需要把ExitCodeExceptionMapper注册成Spring Bean
 */
public class ExitCodeExceptionMapperBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ExitCodeExceptionMapperBootstrap.class)
                .listeners(event -> {
                    if(event instanceof ApplicationReadyEvent){
                        throw new RuntimeException("故意抛出个异常！");
                    }
                })
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }

    @Bean
    public ExitCodeExceptionMapper exitCodeExceptionMapper(){
        return (throwable) -> 128;
    }
}
