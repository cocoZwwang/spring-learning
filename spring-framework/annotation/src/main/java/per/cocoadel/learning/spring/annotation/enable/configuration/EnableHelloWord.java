package per.cocoadel.learning.spring.annotation.enable.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWordConfiguration.class)
public @interface EnableHelloWord {
}
