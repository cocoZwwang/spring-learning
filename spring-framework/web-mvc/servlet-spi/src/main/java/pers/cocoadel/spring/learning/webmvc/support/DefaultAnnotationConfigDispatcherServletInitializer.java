package pers.cocoadel.spring.learning.webmvc.support;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pers.cocoadel.spring.learning.webmvc.config.DispatcherServletConfiguration;

public class DefaultAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DispatcherServletConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
