package per.cocoadel.learning.srping.application.state.run;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class HelloWorldApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("HelloWorldApplicationContextInitializer: Hello World!");
    }
}
