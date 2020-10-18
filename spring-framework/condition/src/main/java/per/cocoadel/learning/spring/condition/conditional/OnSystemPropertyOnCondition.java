package per.cocoadel.learning.spring.condition.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class OnSystemPropertyOnCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> map = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String name = (String) map.get("name");
        String  value = (String) map.get("value");
        String sysValue = System.getProperty(name);
        return ObjectUtils.nullSafeEquals(value,sysValue);
    }
}
