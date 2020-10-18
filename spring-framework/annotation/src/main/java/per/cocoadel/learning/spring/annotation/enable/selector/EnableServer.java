package per.cocoadel.learning.spring.annotation.enable.selector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(EnableServerImportSelector.class)
@Import(EnableServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

    Server.Type type();
}
