package per.cocoadel.learning.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * ApplicationListener 泛型事件监听
 * @EventListener 和 ApplicationListener API本身都支持泛型事件的监听
 * @EventListener 还直接支持泛型参数的监听，不需要用ApplicationEvent作为方法参数
 */
public class GenericEventListenerBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册UserEventListener为 Spring Bean
        context.register(UserEventListener.class);
        context.refresh();
        //发布事件
        User user = new User("ruby");
        GenericEvent<User> userGenericEvent = new GenericEvent<>(user);
        context.publishEvent(userGenericEvent);
        //直接发布事件源
        context.publishEvent(new User("weiss"));
        context.close();
    }

    public static class User {

        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class  GenericEvent<T> extends ApplicationEvent implements ResolvableTypeProvider{
        public GenericEvent(T source) {
            super(source);
        }

        @Override
        public ResolvableType getResolvableType() {
            return ResolvableType.forClassWithGenerics(getClass(),
                    ResolvableType.forInstance(getSource()));
        }

        public T getSource(){
            return (T) super.getSource();
        }
    }

    private static class UserEventListener implements ApplicationListener<GenericEvent<User>> {

        //直接监听泛型参数，不需要事件作为方法参数
        //要求Spring framework 4.2或者更高的版本
        @EventListener
        public void onUser(User user){
            System.out.println("onUser: " + user);
        }

        @EventListener
        public void onUserEvent(GenericEvent<User> event){
            System.out.println("onUserEvent: " + event.getSource().toString());
        }


        /**
         * ApplicationListener API方法
         */
        @Override
        public void onApplicationEvent(GenericEvent<User> event) {
            System.out.println("onApplicationEvent: " + event.getSource().toString());
        }
    }
}
