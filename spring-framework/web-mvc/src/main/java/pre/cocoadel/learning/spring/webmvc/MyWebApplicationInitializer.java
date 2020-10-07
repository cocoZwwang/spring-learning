package pre.cocoadel.learning.spring.webmvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return of(MyWebApplicationBootstrap.class);
    }

    @Override
    protected String[] getServletMappings() {
        return of("/*");
    }

    private <T> T[] of(T... t){
        return t;
    }
}
