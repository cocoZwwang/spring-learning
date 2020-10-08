package pre.cocoadel.learning.spring.ioc.overview.javabeans;

import java.beans.*;

/**
 * 一个java beans 规范 beanInfo的展示demo
 */
public class BeanInfoBootstrap {
    public static void main(String[] args) throws IntrospectionException {
        // 第一个参数：自省的目标，第二个参数：到那一层父类停止自省层
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        // PropertyDescriptor属性描述，这个东西在Spring 中被大量用到
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
            System.out.println(propertyDescriptor.toString());
            String propertyName = propertyDescriptor.getName();
            if("age".equals(propertyName)){
                //类型转换String to Integer
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
            }
        }
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
