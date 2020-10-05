package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * EventPublishingRunListener#contextLoaded(ConfigurableApplicationContext)在spring boot 1.0~2.0里实现是一致的
 * 此时ApplicationContext还没有启动，所以可以通过监听事件ApplicationPreparedEvent来调整SpringApplication和ApplicationContext对象
 */
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        context.setId("context-ruby");

        System.out.println("当前spring应用上下文Id： " + context.getId());

    }
}
