package pers.cocoadel.learning.spring.bean.definition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import pre.cocoadel.learning.spring.ioc.overview.domain.User;

public class ImportUserSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{User.class.getName()};
    }
}
