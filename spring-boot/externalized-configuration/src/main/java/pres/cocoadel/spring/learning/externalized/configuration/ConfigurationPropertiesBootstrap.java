package pres.cocoadel.spring.learning.externalized.configuration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pres.cocoadel.spring.learning.externalized.configuration.domain.UserProperties;

@EnableAutoConfiguration
public class ConfigurationPropertiesBootstrap {

    @Bean
    //@ConfigurationProperties 注解同样可以用在方法上面
    //如果是使用第三方jar包，因为没有对其POJO的控制权，没办法把注解直接标注在类上，可以使用这种方式进行属性注入
//    @ConfigurationProperties(prefix = "user")
    @ConditionalOnProperty(name = "user.city.post-code",havingValue = "0668")
//    @ConditionalOnProperty(name = "user.city.post-code",havingValue = "0667",matchIfMissing = false)
//    @ConditionalOnProperty(name = "user.city.post-code",matchIfMissing = true)
    public UserProperties userProperties() {
        return new UserProperties();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ConfigurationPropertiesBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);
        UserProperties userProperties = context.getBean(UserProperties.class);
        System.out.println(userProperties);
        context.close();
    }
}
