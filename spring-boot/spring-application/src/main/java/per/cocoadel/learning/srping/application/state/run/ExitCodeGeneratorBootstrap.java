package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExitCodeGeneratorBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
//        ExitCodeGenerator exitCodeGenerator = context.getBean("exitCodeGenerator",ExitCodeGenerator.class);
//        SpringApplication.exit(context,exitCodeGenerator);
        int exitCode = SpringApplication.exit(context,exitCodeGenerator());
        System.exit(exitCode);
    }

//    @Bean
    public static ExitCodeGenerator exitCodeGenerator(){
        System.out.println("ExitCodeGenerator bean created!");
        return () -> {
            System.out.println("执行退出码（88）生成。。。");
            return 88;
        };
    }
}
