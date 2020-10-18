package per.cocoadel.learning.spring.condition.before3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring framework 在3.0班班之前使用环境变量 + 占位符 + import标签实现 环境装配 列子
 */
public class ConfigurationApplicationContextBootstrap {
    static {
        /**
         * 默认环境变量env是prod
         */
        String env = System.getProperty("env","dev");
        System.setProperty("env",env);
    }

    public static void main(String[] args) {
        String location = "classpath:/META-INF/configuration-context.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);
        String name = context.getBean("name", String.class);
        System.out.println("name: " + name);
        context.close();
    }
}
