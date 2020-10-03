package per.cocoadel.spring.starter;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import per.cocoadel.autoconfigure.formatter.Formatter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@EnableAutoConfiguration
public class FormatterStarterBootStrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(FormatterStarterBootStrap.class)
                        .web(WebApplicationType.NONE)
//                        .properties("formatter.enabled=false")
                        .run(args);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "ruby");
        //查找Formatter Bean
        Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
        if(beans.isEmpty()){
            throw new NoSuchBeanDefinitionException(Formatter.class);
        }
        beans.forEach((name, formatter) -> {
            System.out.printf("Formatter Bean name: %s --- 格式化后内容：%s\n", name, formatter.format(map));
        });

        context.close();
    }
}
