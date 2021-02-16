package pres.cocoadel.spring.learning.externalized.configuration.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ExtendPropertySourceAppListener implements SpringApplicationRunListener, Ordered
{
    private final SpringApplication springApplication;

    private final String[] args;

    public ExtendPropertySourceAppListener(SpringApplication springApplication, String[] args) {
        this.springApplication = springApplication;
        this.args = args;
    }


    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor
        MutablePropertySources mutablePropertySources  = environment.getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",66);
        MapPropertySource mapPropertySource = new MapPropertySource("from ExtendPropertySourceAppListener",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources  = environment.getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",67);
        MapPropertySource mapPropertySource = new MapPropertySource("from contextPrepared",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }

    // 这里使用 applicationPreparedEvent 效果也是一样的，而且能屏蔽底层的 API 更加好
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources  = environment.getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",68);
        MapPropertySource mapPropertySource = new MapPropertySource("from contextLoaded",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        return new EventPublishingRunListener(springApplication,args).getOrder() + 1;
    }
}
