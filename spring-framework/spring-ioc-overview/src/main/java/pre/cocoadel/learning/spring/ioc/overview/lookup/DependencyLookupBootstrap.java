package pre.cocoadel.learning.spring.ioc.overview.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pre.cocoadel.learning.spring.ioc.overview.annotation.Super;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * Spring IoC 容器依赖查找demo
 */
public class DependencyLookupBootstrap {

    public static void main(String[] args) {
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        BeanFactory beanFactory =new ClassPathXmlApplicationContext(location);

        lookupRealTimeByName(beanFactory);
        lookupLazyByName(beanFactory);

        lookupSingletonByType(beanFactory);
        lookupBySingletonByTypeAndName(beanFactory);
        lookupCollectionByType(beanFactory);

        lookupByAnnotation(beanFactory);

    }

    /**
     *  根据名称实时查找
     */
    private static void lookupRealTimeByName(BeanFactory beanFactory){
        User user = (User) beanFactory.getBean("user");
        System.out.printf("根据【名称：%s】实时查找 bean：%s\n","user",user.toString());
    }

    /**
     * 根据名称延迟查找
     */
    private static void lookupLazyByName(BeanFactory beanFactory){
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("userFactory");
        User user = userObjectFactory.getObject();
        System.out.printf("根据【名称：%s】延迟查找 bean：%s\n","user",user.toString());
    }

    /**
     * 根据类型单个查找
     * 如果当前类型在容器中存在多个Bean实例，会抛出异常{@link NoUniqueBeanDefinitionException}
     * 如果在某个Bean上标注了primary="true"则表明这是主Bean，不会抛出异常，会直接返回主Bean
     */
    private static void lookupSingletonByType(BeanFactory beanFactory){
        try {
            User user = beanFactory.getBean(User.class);
            System.out.printf("根据【类型：%s】实时查找 bean：%s\n",User.class.getSimpleName(),user.toString());
        }catch (NoUniqueBeanDefinitionException e){
            System.out.printf("根据【类型：%s】实时查找 bean 发生异常，存在多个bean\n",User.class.getSimpleName());
        }
    }

    /**
     * 根据类型和名称查询单个Bean
     */
    private static void lookupBySingletonByTypeAndName(BeanFactory beanFactory){
        User user = beanFactory.getBean("user",User.class);
        System.out.printf("根据【类型：%s】和【名称：%s】实时查找 bean：%s\n",User.class.getSimpleName(),"user",user.toString());
    }

    /**
     * 根据类型查询Bean集合
     */
    private static void lookupCollectionByType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.printf("根据【类型：%s】查找bean集合大小：%s\n",User.class.getSimpleName(),map.size());
            map.forEach((name,user) ->{
                System.out.printf("根据【类型：%s】集合查找-【bean name:%s】：%s\n",User.class.getSimpleName(),name,user.toString());
            });
        }
    }

    /**
     * 根据注解查找Bean集合
     */
    private static void lookupByAnnotation(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,Object> map = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.printf("根据【注解：%s】查找bean集合大小：%s\n",Super.class.getSimpleName(),map.size());
            map.forEach((name,user) ->{
                System.out.printf("根据【注解：%s】集合查找-【bean name:%s】：%s\n",Super.class.getSimpleName(),name,user.toString());
            });
        }
    }
}
