package pres.cocoadel.spring.learning.externalized.configuration.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ExtendPropertySourceEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor

        MutablePropertySources mutablePropertySources  = environment.getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",22);
        MapPropertySource mapPropertySource = new MapPropertySource("from ExtendPropertySourceEnvironmentPostProcessor",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }


    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }
}
