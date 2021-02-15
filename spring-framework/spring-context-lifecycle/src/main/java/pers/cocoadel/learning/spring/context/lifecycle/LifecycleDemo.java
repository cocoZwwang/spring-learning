package pers.cocoadel.learning.spring.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

/**
 *  {@link org.springframework.context.Lifecycle} Bean 示例
 */
public class LifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBeanDefinition("myLifecycle",
                BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        context.refresh();
        //显式调用 Application start 方法
        context.start();
        //显式调用 Application stop 方法
        context.stop();
        context.close();
    }
}

