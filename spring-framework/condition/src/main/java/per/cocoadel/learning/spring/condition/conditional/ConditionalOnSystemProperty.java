package per.cocoadel.learning.spring.condition.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({OnSystemPropertyOnCondition.class})
public @interface ConditionalOnSystemProperty {
    String name();

    String value();
}
