package pers.cocoadel.spring.learning.web.rest.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import pers.cocoadel.spring.learning.web.rest.http.converter.properties.Properties2HttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Properties.class.equals(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Properties2HttpMessageConverter converter = new Properties2HttpMessageConverter();
        Properties properties = (Properties) returnValue;

        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        MediaType mediaType = MediaType.parseMediaType(request.getContentType());

        HttpServletResponse response = servletWebRequest.getResponse();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

        converter.write(properties, mediaType, outputMessage);
        mavContainer.setRequestHandled(true);
    }
}
