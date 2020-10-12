package per.cocoadel.learning.spring.annotation.meta;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 通过{@link MetadataReader} 读取{@link AnnotationMetadata}
 * 再通过AnnotationMetadata获取所有元注解信息和其属性
 */
@TransactionService(name = "cocoAdel")
public class AnnotationMetaBootstrap {
    public static void main(String[] args) throws IOException {
        //AnnotationMetaBootstrap.class的类信息封装
        AnnotationMetadata annotationMetadata = getAnnotationMetadataByAsm(); //通过ASM读取
//        AnnotationMetadata annotationMetadata = getAnnotationMetadataByReflection(); //通过Java反射读取
        //获取AnnotationMetaBootstrap.class的直接注解
        Set<String> annotations = annotationMetadata.getAnnotationTypes();
        System.out.println("直接注解集合：" + annotations);
        annotations.forEach(annotationName -> {
            //获取某个直接注解的所有元注解，包括递归层次处理
            Set<String> metaAnnotationNames = annotationMetadata.getMetaAnnotationTypes(annotationName);
            System.out.println("直接注解：" + annotationName + "==============================");
            metaAnnotationNames.forEach(metaAnnotationName -> {
                System.out.printf("【元注解：%s】\n", metaAnnotationName);
                //获取元注解属性
                Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(metaAnnotationName);
                if (attributes != null) {
                    attributes.forEach((name, value) -> {
                        System.out.printf("【元注解：%s】【属性：%s】：%s\n", metaAnnotationName, name, value);
                    });
                }
            });
        });
    }

    /**
     * 通过ASM获取AnnotationMetadata信息
     */
    private static AnnotationMetadata getAnnotationMetadataByAsm() throws IOException {
        String className = AnnotationMetaBootstrap.class.getName();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
        return metadataReader.getAnnotationMetadata();
    }

    /**
     * 通过Java反射获取AnnotationMetadata信息
     */
    private static AnnotationMetadata getAnnotationMetadataByReflection() {
        //spring  framework 5.2之前的API
//        return new StandardAnnotationMetadata(AnnotationMetaBootstrap.class);
        //Spring  framework 5.2之后的API
        return AnnotationMetadata.introspect(AnnotationMetaBootstrap.class);
    }
}
