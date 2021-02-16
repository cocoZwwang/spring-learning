package pres.cocoadel.spring.learning.externalized.configuration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;


@EnableAutoConfiguration
@PropertySource(name = "from Bootstrap PropertySource",value = "classpath:/META-INF/default.properties")
@Configuration
public class ExtendPropertySourceBootstrap {

    public static void main(String[] args) {
        String[] array = new String[args == null ? 1 : args.length + 1];
        if (args != null) {
            System.arraycopy(args,0,array,0,args.length);
        }
        array[array.length - 1] = "--user.id=12";
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ExtendPropertySourceBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .properties("user.id = 33")
                        .run(array);

        ConfigurableEnvironment environment = context.getEnvironment();

        System.out.printf("user id : %d\n",environment.getProperty("user.id",Long.class));

        environment.getPropertySources()
                .forEach(propertySource ->
                        System.out.printf("propertySource[名称：%s] - %s\n",propertySource.getName(),propertySource));
        context.close();
    }

}
