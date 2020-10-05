package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class EmptyBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EmptyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
