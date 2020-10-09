package pre.cocoadel.learning.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 直接使用{@link DefaultListableBeanFactory}作为IoC容器
 */
public class BeanFactoryAsIoCBootstrap {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("注册Bean 数量：" + beanDefinitionsCount);

        lookupCollectionByType(beanFactory);
    }

    /**
     * 根据类型查询Bean集合
     */
    private static void lookupCollectionByType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.printf("\n根据【类型：%s】查找bean集合大小：%s\n",User.class.getSimpleName(),map.size());
            map.forEach((name,user) ->{
                System.out.printf("根据【类型：%s】集合查找-【bean name:%s】：%s\n",User.class.getSimpleName(),name,user.toString());
            });
        }
    }
}
