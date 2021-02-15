package pers.cocoadel.spring.learning.templateengine.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setViewClass(JstlView.class);
        resourceViewResolver.setPrefix("/WEB-INF/jsp/");
        resourceViewResolver.setSuffix(".jsp");
        resourceViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        return resourceViewResolver;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> baseDocCustomizer() {
        return factory -> {
            factory.addContextCustomizers(context -> {
                //注意这里前面不能带 “/” 否则会当作根路径处理，这里要作为相对路径处理
                String relativePath = "spring-boot/spring-boot-view/src/main/webapp";
                // 绝对路径是 ${user.dir}
                File file = new File(relativePath);
                // 如果文件存在，则设置，这样能兼容 jar 包 和 idea 环境
                if (file.exists()) {
                    context.setDocBase(file.getAbsolutePath());
                }
            });
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {
                System.out.println("拦截中...");
                return true;
            }
        });
    }
}
