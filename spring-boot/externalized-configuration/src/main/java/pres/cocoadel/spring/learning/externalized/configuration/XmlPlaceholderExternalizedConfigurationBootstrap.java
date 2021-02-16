package pres.cocoadel.spring.learning.externalized.configuration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import pres.cocoadel.spring.learning.externalized.configuration.domain.User;

@ImportResource("/META-INF/spring/user-context.xml")
@EnableAutoConfiguration
public class XmlPlaceholderExternalizedConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        //系统属性 System.getProperty("user.name") 比 application.properties 中配置的 {user.name} 优先级要高
        User user = context.getBean(User.class);
        System.out.println(user);

        context.close();
    }
}
