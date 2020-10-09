package pre.cocoadel.learning.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 使用ApplicationContext 作为Spring IoC容器
 */
public class AnnotationApplicationContextAsIoCBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationApplicationContextAsIoCBootstrap.class);
        context.refresh();
        lookupCollectionByType(context);
        context.close();
    }

    @Bean
    public User user() {
        return new User();
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
