package per.cocoadel.learning.spring.annotation.enable.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//Configuration Class 作为导入源
@Import(HelloWordConfiguration.class)
public @interface EnableHelloWord {
}
