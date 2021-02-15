package pers.cocoadel.spring.learning.boot.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import pers.cocoadel.spring.learning.aync.servlet.method.support.AsyncServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

@EnableAutoConfiguration
//注册 Controller
@ComponentScan(basePackages = "pers.cocoadel.spring.learning.aync.servlet")
//扫描并且处理 标记了 @Web* 注解的类
@ServletComponentScan(basePackages = "pers.cocoadel.spring.learning.aync.servlet")
public class SpringBootServletBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletBootstrap.class, args);
    }


    /**
     * 可以通过优先级的方式去覆盖 DispatcherServlet 的映射
     * 使用传统容器部署spring boot 应用的时候，相同名字的 Servlet 不会被重复注册，
     * {@link AsyncServlet 的注解已经标记了 asyncServlet 这个名字了}，所以需要取不同的名字
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public ServletRegistrationBean<AsyncServlet> asyncServletServletRegistrationBean() {
        ServletRegistrationBean<AsyncServlet> registrationBean =
                new ServletRegistrationBean<>(new AsyncServlet(), "/");
        registrationBean.setName("MyAsyncServlet");
        return registrationBean;
    }

    /**
     * 为什么不会产生日志？
     * ServletContextInitializer 的日志输出由其子类自己去实现
     * 这里的lambda 表达式没有实现日志输出，因此没有输出
     */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("filter", filter);
            filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }
}
