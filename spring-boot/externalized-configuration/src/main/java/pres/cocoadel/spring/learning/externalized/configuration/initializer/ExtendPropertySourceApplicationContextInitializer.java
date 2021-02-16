package pres.cocoadel.spring.learning.externalized.configuration.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ExtendPropertySourceApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources mutablePropertySources  = environment.getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",99);
        MapPropertySource mapPropertySource = new MapPropertySource("from ExtendPropertySourceApplicationContextInitializer",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }
}
