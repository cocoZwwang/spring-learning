package pers.cocoadel.spring.learning.web.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import pers.cocoadel.spring.learning.web.rest.http.converter.properties.Properties2HttpMessageConverter;
import pers.cocoadel.spring.learning.web.rest.method.support.PropertiesHandlerMethodArgumentResolver;
import pers.cocoadel.spring.learning.web.rest.method.support.PropertiesHandlerMethodReturnValueHandler;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new LinkedList<>();
        newArgumentResolvers.add(new PropertiesHandlerMethodArgumentResolver());
        if (!CollectionUtils.isEmpty(argumentResolvers)) {
            newArgumentResolvers.addAll(argumentResolvers);
        }
        requestMappingHandlerAdapter.setArgumentResolvers(newArgumentResolvers);

        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new LinkedList<>();
        newReturnValueHandlers.add(new PropertiesHandlerMethodReturnValueHandler());
        if (!CollectionUtils.isEmpty(returnValueHandlers)) {
            newReturnValueHandlers.addAll(returnValueHandlers);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.set(0, new Properties2HttpMessageConverter());
//    }
}
