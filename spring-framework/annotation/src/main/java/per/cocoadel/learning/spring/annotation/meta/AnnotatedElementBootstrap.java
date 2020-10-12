package per.cocoadel.learning.spring.annotation.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.ReflectionUtils.*;

/**
 * 通过{@link java.lang.reflect.AnnotatedElement} 获取元注解信息
 */
@TransactionService(value = "ruby")
public class AnnotatedElementBootstrap {
    public static void main(String[] args) {
        AnnotatedElement annotatedElement = AnnotatedElementBootstrap.class;
        Annotation transactionAnnotation = annotatedElement.getAnnotation(TransactionService.class);
        Set<Annotation> metaAnnotations = new HashSet<>();
        getAllMetaAnnotations(transactionAnnotation,metaAnnotations);
        printAnnotation(transactionAnnotation);
        metaAnnotations.forEach(AnnotatedElementBootstrap::printAnnotation);
    }

    /**
     * 递归获取目标Annotation的所有元注解
     */
    private static void getAllMetaAnnotations(Annotation annotation,Set<Annotation> metaAnnotations) {
        AnnotatedElement annotatedElement = annotation.annotationType();
        Annotation[] annotations = annotatedElement.getAnnotations();
        for(Annotation a : annotations){
            if(!metaAnnotations.contains(a)){
                metaAnnotations.add(a);
                getAllMetaAnnotations(a,metaAnnotations);
            }
        }
    }

    /**
     * 通过Java反射调用目标Annotation的方法，并且打印
     */
    private static void printAnnotation(Annotation annotation){
        Class<?> clazz = annotation.annotationType();
        doWithMethods(clazz,
                method -> System.out.printf("%s.%s(): %s\n", clazz.getName(), method.getName(),
                        invokeMethod(method, annotation)),
                //排除Annotation.class的方法
                method -> method.getParameterCount() == 0 && !method.getDeclaringClass().equals(Annotation.class));
    }
}
