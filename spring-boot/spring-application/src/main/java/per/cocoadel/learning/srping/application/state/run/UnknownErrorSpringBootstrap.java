package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class UnknownErrorSpringBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UnknownErrorSpringBootstrap.class)
                .web(WebApplicationType.NONE)
                .initializers(context -> {
                    throw new UnknownError("故意抛出异常！");
                })
                .run(args)
                .close();
    }
}
