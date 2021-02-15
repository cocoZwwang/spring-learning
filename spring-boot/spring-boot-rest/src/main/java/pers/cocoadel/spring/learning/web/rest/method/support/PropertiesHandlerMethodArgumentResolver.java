package pers.cocoadel.spring.learning.web.rest.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pers.cocoadel.spring.learning.web.rest.http.converter.properties.Properties2HttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Properties2HttpMessageConverter properties2HttpMessageConverter = new Properties2HttpMessageConverter();
        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        return properties2HttpMessageConverter.read(null,null,inputMessage);
    }
}
