package pers.cocoadel.learning.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * PropertySource 动态替换示例
 */
public class EnvironmentPropertySourceChangeDemo {

    //默认的情况下是使用System.getProperties("user.name");
    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);

        MutablePropertySources mutablePropertySources = context.getEnvironment().getPropertySources();
        //添加自定义PropertySource
        Map<String,Object> map = new HashMap<>();
        MapPropertySource mapPropertySource = new MapPropertySource("first-property-source",map);
        map.put("user.name","ruby");
        mutablePropertySources.addFirst(mapPropertySource);

        //上下文装载之前
        context.refresh();
        //这里添加的值，不会对上面的userName的值产生影响，因为userName是在Spring上下文启动的过程中就已经加载了。
        //这里的PropertySource是不具备刷新能力的。
        map.put("user.name","weiss");

        EnvironmentPropertySourceChangeDemo demo = context.getBean(EnvironmentPropertySourceChangeDemo.class);
        //如果没有添加上面的自定义属性，会打印系统属性的user.name，默认是系统用户名
        //如果添加了上面的自定义属性，会打印ruby
        System.out.println(demo.userName);

        for (PropertySource<?> source : mutablePropertySources) {
            System.out.printf("propertySource（name=%s）, user.name = %s\n",source.getName(),source.getProperty("user.name"));
        }

        context.close();
    }
}
