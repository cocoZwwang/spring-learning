package pres.cocoadel.spring.learning.externalized.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ExtendPropertySourceEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        //1. from contextLoaded
        //2. from contextPrepared
        //3. from ExtendPropertySourceApplicationContextInitializer
        //4. from ExtendPropertySourceAppListener
        //5. from ExtendPropertySourceEventListener
        //6. from ExtendPropertySourceEnvironmentPostProcessor
        MutablePropertySources mutablePropertySources  = event.getEnvironment().getPropertySources();
        Map<String,Object> map = new HashMap<>();
        map.put("user.id",55);
        MapPropertySource mapPropertySource = new MapPropertySource("from ExtendPropertySourceEventListener",map);
        //这里是 addFirst，后添加的会排到前面
        mutablePropertySources.addFirst(mapPropertySource);
    }
}
