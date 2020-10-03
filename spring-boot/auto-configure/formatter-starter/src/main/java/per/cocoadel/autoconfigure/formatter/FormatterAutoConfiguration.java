package per.cocoadel.autoconfigure.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnNotWebApplication
//@ConditionalOnWebApplication
//如果配置参数formatter.enabled= true时候则匹配，如果为false则不匹配，如果缺省就默认为true
//@ConditionalOnProperty(prefix = "formatter",name = "enabled",havingValue = "true",matchIfMissing = true)
//等同于上面的属性匹配
@ConditionalOnExpression("${formatter.enabled:true}")
//如果Classpath路径下存在META-INF/spring.factories资源文件则匹配
@ConditionalOnResource(resources = "META-INF/spring.factories")
public class FormatterAutoConfiguration {

    /**
     * 当ObjectMapper.Class不存在Classpath下时，Bean为defaultFormatter实例
     */
    @ConditionalOnMissingClass("com.fasterxml.jackson.databind.ObjectMapper")
    @Bean
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    /**
     * 当ObjectMapper.Class存在，但是不存在ObjectMapper Bean不存在时候，使用默认构造器，自己创建ObjectMapper对象
     */
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    @ConditionalOnMissingBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
    @Bean
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }

    /**
     * 当ObjectMapper.Class存在，同时存在ObjectMapper Bean的时候，直接通过构造器注入ObjectMapper Bean
     */
    @ConditionalOnBean(ObjectMapper.class)
    @Bean
    public Formatter objectMapperFormatter(ObjectMapper objectMapper){
        return new JsonFormatter(objectMapper);
    }
}

